package kz.epam.waterdelivery.servlet;

import kz.epam.waterdelivery.command.Command;
import kz.epam.waterdelivery.command.CommandContainer;
import kz.epam.waterdelivery.command.CommandResult;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String commandName = request.getMethod() + request.getPathInfo();
        Command command = CommandContainer.get(commandName);
        if (command == null) {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "url.not.found");
            } catch (IOException e) {

                throw new ServletException(e);
            }
            return;
        }
        CommandResult result = null;
        try {
            result = command.execute(request);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        if (result.isRedirection()) {
            response.sendRedirect(request.getContextPath() + "/" + result.getView());
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
