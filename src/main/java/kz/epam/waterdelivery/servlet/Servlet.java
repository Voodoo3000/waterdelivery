package kz.epam.waterdelivery.servlet;

import kz.epam.waterdelivery.command.Command;
import kz.epam.waterdelivery.command.CommandContainer;
import kz.epam.waterdelivery.command.CommandException;
import kz.epam.waterdelivery.command.CommandResult;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Servlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Servlet.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String commandName = request.getMethod() + request.getPathInfo();
        Command command = CommandContainer.get(commandName);
        if (command == null) {
            try {
                response.sendRedirect("error404");
            } catch (IOException e) {
                throw new ServletException(e);
            }
            return;
        }
        CommandResult result = null;
        try {
            result = command.execute(request);
        } catch (CommandException e) {
            LOGGER.error("Execute command error", e);
            response.sendRedirect("error500");
        }
        if (result != null) {
            if (result.isRedirection()) {
                response.sendRedirect(request.getContextPath() + "/" + result.getView());
                return;
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp");
            requestDispatcher.forward(request, response);
        } else {
            LOGGER.error("Result is null");
            response.sendRedirect("error500");
        }
    }
}
