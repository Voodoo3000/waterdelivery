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

public class SignUpCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);
    private CommandResult result = new CommandResult();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(Entity.RB_NAME, locale);
        String busy_login_err_msg = RB.getString(Entity.ERROR_BUSY_LOGIN);
        String pass_mis_err_msg = RB.getString(Entity.ERROR_PASS_MISMATCH);
        String invalid_loginEmail_err_msg = RB.getString(Entity.ERROR_INVALID_LOGINEMAIL);
        String undesirable_pass_err_msg = RB.getString(Entity.ERROR_UNDESIRABLE_PASSWORD);
        String invalid_firstname_err_msg = RB.getString(Entity.ERROR_INVALID_FIRSTNAME);
        String invalid_lastname_err_msg = RB.getString(Entity.ERROR_INVALID_LASTNAME);

        String loginEmail = request.getParameter(Entity.PARAM_LOGIN_EMAIL);
        String newPassword = request.getParameter(Entity.PARAM_NEW_PASSWORD);
        String newRePassword = request.getParameter(Entity.PARAM_RE_NEW_PASSWORD);
        String firstName = request.getParameter(Entity.PARAM_FIRSTNAME);
        String lastName = request.getParameter(Entity.PARAM_LASTNAME);
        UserDao userDao = new UserDao();
        User user;

        boolean loginEmailValidResult;
        boolean passwordValidResult;
        boolean firstNameValidResult;
        boolean lastNameValidResult;
        loginEmailValidResult = Validator.validateLoginEmail(loginEmail);
        passwordValidResult = Validator.validatePassword(newPassword);
        firstNameValidResult = Validator.validateNames(firstName);
        lastNameValidResult = Validator.validateNames(lastName);
        try {
            if (!loginEmailValidResult) {
                LOGGER.info("Invalid login(email address)");
                session.setAttribute(Entity.ERROR, invalid_loginEmail_err_msg);
            } else if (!passwordValidResult) {
                LOGGER.info("Invalid login(email address)");
                session.setAttribute(Entity.ERROR, undesirable_pass_err_msg);
            } else if (!firstNameValidResult) {
                LOGGER.info("Invalid first name");
                session.setAttribute(Entity.ERROR, invalid_firstname_err_msg);
            } else if (!lastNameValidResult) {
                LOGGER.info("Invalid last name");
                session.setAttribute(Entity.ERROR, invalid_lastname_err_msg);
            } else if (userDao.getByLogin(loginEmail) != null) {
                LOGGER.info("Entered login is busy");
                session.setAttribute(Entity.ERROR, busy_login_err_msg);
            } else if (!newPassword.equals(newRePassword)) {
                LOGGER.info("Password mismatching");
                session.setAttribute(Entity.ERROR, pass_mis_err_msg);
            } else {
                user = createUser(loginEmail, newPassword, firstName, lastName, userDao);
                LOGGER.info("Newly registered user is: " + user.toString());
                session.setAttribute(Entity.ATTR_USER, user);
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in SignUpCommand", e);
            throw new CommandException(e);
        }
        return Entity.MAIN;
    }

    private User createUser(String loginEmail, String password, String firstName, String lastName, UserDao userDao) throws DaoException {
        User user;
        user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLoginEmail(loginEmail);
        user.setPassword(password);
        user.setRole(User.Role.CLIENT);
        user.setWallet(0);
        user.setState(User.State.ENABLED);
        userDao.add(user);
        return userDao.getByLogin(loginEmail);
    }

    private CommandResult validUserFields(HttpServletRequest request, String loginEmail, String password, String firstName, String lastName) {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(Entity.RB_NAME, locale);
        String invalid_loginEmail_err_msg = RB.getString(Entity.ERROR_INVALID_LOGINEMAIL);
        String undesirable_pass_err_msg = RB.getString(Entity.ERROR_UNDESIRABLE_PASSWORD);
        String invalid_firstname_err_msg = RB.getString(Entity.ERROR_INVALID_FIRSTNAME);
        String invalid_lastname_err_msg = RB.getString(Entity.ERROR_INVALID_LASTNAME);
        boolean loginEmailValidResult;
        boolean passwordValidResult;
        boolean firstNameValidResult;
        boolean lastNameValidResult;
        loginEmailValidResult = Validator.validateLoginEmail(loginEmail);
        passwordValidResult = Validator.validatePassword(password);
        firstNameValidResult = Validator.validateNames(firstName);
        lastNameValidResult = Validator.validateNames(lastName);
        if (!loginEmailValidResult) {
            LOGGER.info("Invalid login(email address)");
            session.setAttribute(Entity.ERROR, invalid_loginEmail_err_msg);
            result = Entity.MAIN;
        } else if (!passwordValidResult) {
            LOGGER.info("Invalid login(email address)");
            session.setAttribute(Entity.ERROR, undesirable_pass_err_msg);
            result = Entity.MAIN;
        } else if (!firstNameValidResult) {
            LOGGER.info("Invalid first name");
            session.setAttribute(Entity.ERROR, invalid_firstname_err_msg);
            result = Entity.MAIN;
        } else if (!lastNameValidResult) {
            LOGGER.info("Invalid last name");
            session.setAttribute(Entity.ERROR, invalid_lastname_err_msg);
            result = Entity.MAIN;
        }
        return result;
    }
}
