package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class SignInCommand implements Command {
    private static final String RB_NAME = "i18n.message";
    private static final String ATTR_LOCALE = "locale";
    private static final String ERROR = "errormsg";
    private static final String ERROR_LOGIN_PASS = "error.wrong_user_or_pass";
    private static String login_pass_err_msg;
    private static final CommandResult ADMIN_PAGE = new CommandResult("do/open_admin_page", true);
    private static final CommandResult MAIN = new CommandResult("do/main", true);
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTR_USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        Locale locale = (Locale) context.getAttribute(ATTR_LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        login_pass_err_msg = RB.getString(ERROR_LOGIN_PASS);
        CommandResult result;

        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        UserDao userDao = new UserDao();
        User user;

        user = userDao.getByLogin(email);
        if (user == null || !user.getLoginEmail().equals(email)
                || !user.getPassword().equals(password) || user.getState() == User.State.DISABLED) {
            result = MAIN;
            session.setAttribute(ERROR, login_pass_err_msg);
        } else if (user.getRole() == User.Role.ADMIN) {
            request.getSession().setAttribute(ATTR_USER, user);
            result = ADMIN_PAGE;
        } else {
            request.getSession().setAttribute(ATTR_USER, user);
            result = MAIN;
        }

        return result;
    }
}
