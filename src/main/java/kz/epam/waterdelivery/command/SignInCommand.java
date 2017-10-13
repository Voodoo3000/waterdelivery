package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;


public class SignInCommand implements Command {
    private static final String RB_NAME = "i18n.message";
    private static final String ATTR_LOCALE = "locale";
    private static final String ERROR = "errormsg";
    private static final String ERROR_LOGIN_PASS = "error.wrong_user_or_pass";
    private static String login_pass_err_msg;
    private static final CommandResult AUTHORIZED = new CommandResult("do/authorized", true);
    private static final CommandResult LOGIN_FAILED = new CommandResult("do/main", true);

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        Locale locale = (Locale) context.getAttribute(ATTR_LOCALE);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        login_pass_err_msg = RB.getString(ERROR_LOGIN_PASS);
        CommandResult result = null;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user;
        try {
            user = userDao.getByLogin(email);
            if (user == null || !user.getLoginEmail().equals(email) || !user.getPassword().equals(password)) {
                result = LOGIN_FAILED;
                session.setAttribute(ERROR, login_pass_err_msg);
            } else {
                result = AUTHORIZED;
                request.getSession().setAttribute("user", user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
