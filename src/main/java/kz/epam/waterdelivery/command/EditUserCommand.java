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

public class EditUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditUserCommand.class);
    private static final String RB_NAME = "i18n.message";
    private static final String ERROR = "errormsg";
    private static final String ERROR_PASS = "error.wrong_pass";
    private static final String ERROR_PASS_MISMATCH = "error.password_mismatching";
    private static final CommandResult RESULT = new CommandResult("customer_cabinet");
    private static final String PARAM_LOGIN_EMAIL = "loginEmail";
    private static final String PARAM_CURRENT_PASSWORD = "currentPassword";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    private static final String PARAM_RE_NEW_PASSWORD = "newRePassword";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String ATTR_USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        String pass_err_msg = RB.getString(ERROR_PASS);
        String pass_mis_err_msg = RB.getString(ERROR_PASS_MISMATCH);

        String loginEmail = request.getParameter(PARAM_LOGIN_EMAIL);
        String currentPassword = request.getParameter(PARAM_CURRENT_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        String newRePassword = request.getParameter(PARAM_RE_NEW_PASSWORD);
        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);

        UserDao userDao = new UserDao();
        User user;
        try {
            user = userDao.getByLogin(loginEmail);
            if (!currentPassword.equals(user.getPassword())) {
                session.setAttribute(ERROR, pass_err_msg);
                LOGGER.info("Wrong password");
            } else if (!newPassword.equals(newRePassword)) {
                session.setAttribute(ERROR, pass_mis_err_msg);
                LOGGER.info("Entered password mismatching");
            } else {
                updateUser(user, firstname, lastname, newPassword, userDao, request);
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in EditUserCommand", e);
            throw new CommandException(e);
        }
        return RESULT;
    }

    private void updateUser(User user, String firstname, String lastname, String newPassword, UserDao userDao, HttpServletRequest request) throws DaoException {
        if (!newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }
        user.setFirstName(firstname);
        user.setLastName(lastname);
        userDao.update(user);
        LOGGER.info("User " + user.getLoginEmail() + " was changed by himself");
        request.getSession().setAttribute(ATTR_USER, user);
    }
}
