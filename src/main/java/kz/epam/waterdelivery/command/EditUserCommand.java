package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.util.LocaleUtil;
import kz.epam.waterdelivery.util.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditUserCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(Entity.RB_NAME, locale);
        String passErrMsg = RB.getString(Entity.ERROR_PASS);
        String passMisErrMsg = RB.getString(Entity.ERROR_PASS_MISMATCH);
        String invalidFirstNameErrMsg = RB.getString(Entity.ERROR_INVALID_FIRST_NAME);
        String invalidLastNameErrMsg = RB.getString(Entity.ERROR_INVALID_LAST_NAME);

        String loginEmail = request.getParameter(Entity.PARAM_LOGIN_EMAIL);
        String currentPassword = request.getParameter(Entity.PARAM_CURRENT_PASSWORD);
        String newPassword = request.getParameter(Entity.PARAM_NEW_PASSWORD);
        String newRePassword = request.getParameter(Entity.PARAM_RE_NEW_PASSWORD);
        String firstName = request.getParameter(Entity.PARAM_FIRST_NAME);
        String lastName = request.getParameter(Entity.PARAM_LAST_NAME);

        UserDao userDao = new UserDao();
        User user;

        boolean firstNameValidResult;
        boolean lastNameValidResult;
        firstNameValidResult = Validator.validateNames(firstName);
        lastNameValidResult = Validator.validateNames(lastName);
        try {
            user = userDao.getByLogin(loginEmail);
            if (!firstNameValidResult) {
                LOGGER.info(Entity.FIRST_NAME_MSG);
                session.setAttribute(Entity.ERROR, invalidFirstNameErrMsg);
            } else if (!lastNameValidResult) {
                LOGGER.info(Entity.LAST_NAME_MSG);
                session.setAttribute(Entity.ERROR, invalidLastNameErrMsg);
            } else if (!currentPassword.equals(user.getPassword())) {
                session.setAttribute(Entity.ERROR, passErrMsg);
                LOGGER.info("Wrong password");
            } else if (!newPassword.equals(newRePassword)) {
                session.setAttribute(Entity.ERROR, passMisErrMsg);
                LOGGER.info(Entity.PASS_MSG);
            } else {
                updateUser(user, firstName, lastName, newPassword, userDao, request);
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in EditUserCommand", e);
            throw new CommandException(e);
        }
        return Entity.CUSTOMER_CABINET;
    }

    private void updateUser(User user, String firstName, String lastName, String newPassword, UserDao userDao, HttpServletRequest request) throws DaoException {
        boolean passwordValidResult;
        passwordValidResult = Validator.validatePassword(newPassword);
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(Entity.RB_NAME, locale);
        String undesirable_pass_err_msg = RB.getString(Entity.ERROR_UNDESIRABLE_PASSWORD);
        if (!newPassword.isEmpty()) {
            if (passwordValidResult){
                user.setPassword(newPassword);
            }
            else {
                LOGGER.info("Invalid login(email address)");
                session.setAttribute(Entity.ERROR, undesirable_pass_err_msg);
            }
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userDao.update(user);
        LOGGER.info("User " + user.getLoginEmail() + " was changed by himself");
        request.getSession().setAttribute(Entity.ATTR_USER, user);
    }
}
