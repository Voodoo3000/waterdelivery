package kz.epam.waterdelivery.servlet;

import kz.epam.waterdelivery.command.Command;
import kz.epam.waterdelivery.command.CommandContainer;
import kz.epam.waterdelivery.command.CommandException;
import kz.epam.waterdelivery.command.CommandResult;
import kz.epam.waterdelivery.util.LocaleUtil;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Servlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Servlet.class);
    private static final String RB_NAME = "i18n.message";
    private static final String ATTR_LOCALE = "locale";
    private static final String MESSAGE_ERROR404 = "error.404";
    private static final String MESSAGE_ERROR500 = "error.500";
    private static final String ERROR404 = "error404";
    private static final String ERROR500 = "error500";

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        String url_not_found_err_msg = RB.getString(MESSAGE_ERROR404);
        String internal_err_msg = RB.getString(MESSAGE_ERROR500);
        request.setCharacterEncoding("UTF-8");
        String commandName = request.getMethod() + request.getPathInfo();
        Command command = CommandContainer.get(commandName);
        if (command == null) {
            session.setAttribute(ERROR404, url_not_found_err_msg);
            response.sendRedirect("error");
            return;
        }
        CommandResult result = null;
        try {
            result = command.execute(request);
        } catch (CommandException e) {
            LOGGER.error("Execute command error", e);
        }
        if (result != null) {
            if (result.isRedirection()) {
                response.sendRedirect(request.getContextPath() + "/" + result.getView());
                return;
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp");
            requestDispatcher.forward(request, response);
            session.removeAttribute("errormsg");
            session.removeAttribute(ERROR404);
            session.removeAttribute(ERROR500);
        } else {
            LOGGER.error("Result is null");
            session.setAttribute(ERROR500, internal_err_msg);
            response.sendRedirect("error");
        }
    }
}
