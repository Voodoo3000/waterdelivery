package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.util.LocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;


public class SignInCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);
    private static final String RB_NAME = "i18n.message";
    private static final String ERROR = "errormsg";
    private static final String ERROR_LOGIN_PASS = "error.wrong_user_or_pass";
    private static final String ERROR_USER_BAN = "error.banned_user";
    private static final CommandResult ADMIN_PAGE = new CommandResult("do/open_admin_page", true);
    private static final CommandResult MAIN = new CommandResult("do/main", true);
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTR_USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        String login_pass_err_msg = RB.getString(ERROR_LOGIN_PASS);
        String banned_user_err_msg = RB.getString(ERROR_USER_BAN);

        CommandResult result;
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);

        UserDao userDao;
        User user;
        try {
            userDao = new UserDao();
            user = userDao.getByLogin(email);
        } catch (DaoException e) {
            LOGGER.error("DaoException in SignInCommand", e);
            throw new CommandException(e);
        }
        if (user == null || !user.getLoginEmail().equals(email) || !user.getPassword().equals(password)) {
            LOGGER.info("Wrong login or password");
            session.setAttribute(ERROR, login_pass_err_msg);
            result = null;
        } else if (user.getState() == User.State.DISABLED) {
            LOGGER.info("Requested user was banned");
            session.setAttribute(ERROR, banned_user_err_msg);
            result = MAIN;
        } else if (user.getRole() == User.Role.ADMIN) {
            session.setAttribute(ATTR_USER, user);
            LOGGER.info("Administrator has logged in");
            result = ADMIN_PAGE;
        } else {
            session.setAttribute(ATTR_USER, user);
            LOGGER.info("User " + user.getLoginEmail() + " has logged in");
            result = MAIN;
        }
        return result;
    }
}
