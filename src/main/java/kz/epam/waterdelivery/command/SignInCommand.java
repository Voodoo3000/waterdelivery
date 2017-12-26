package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.util.LocaleUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;


public class SignInCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SignInCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(Entity.RB_NAME, locale);
        String login_pass_err_msg = RB.getString(Entity.ERROR_LOGIN_PASS);
        String banned_user_err_msg = RB.getString(Entity.ERROR_USER_BAN);
        String loginEmail = request.getParameter(Entity.PARAM_LOGIN_EMAIL);
        String currentPassword = request.getParameter(Entity.PARAM_CURRENT_PASSWORD);
        CommandResult result;
        UserDao userDao;
        User user;
        try {
            userDao = new UserDao();
            user = userDao.getByLogin(loginEmail);
        } catch (DaoException e) {
            LOGGER.error("DaoException in SignInCommand", e);
            throw new CommandException(e);
        }
        if (user == null || !user.getLoginEmail().equals(loginEmail) || !user.getPassword().equals(currentPassword)) {
            LOGGER.info("Wrong login or password");
            session.setAttribute(Entity.ERROR, login_pass_err_msg);
            result = Entity.MAIN;
        } else if (user.getState() == User.State.DISABLED) {
            LOGGER.info("Requested user was banned");
            session.setAttribute(Entity.ERROR, banned_user_err_msg);
            result = Entity.MAIN;
        } else if (user.getRole() == User.Role.ADMIN) {
            session.setAttribute(Entity.ATTR_USER, user);
            LOGGER.info("Administrator has logged in");
            result = Entity.MAIN_ADMIN;
        } else {
            session.setAttribute(Entity.ATTR_USER, user);
            LOGGER.info("User " + user.getLoginEmail() + " has logged in");
            result = Entity.MAIN;
        }
        return result;
    }
}
