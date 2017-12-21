package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.util.LocaleUtil;
import kz.epam.waterdelivery.util.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignUpCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SignUpCommand.class);
    private static final String RB_NAME = "i18n.message";
    private static final String ERROR = "errormsg";
    private static final String ERROR_BUSY_LOGIN = "error.busy_login";
    private static final String ERROR_PASS_MISMATCH = "error.password_mismatching";
    private static final String ERROR_INVALID_LOGINEMAIL = "error.invalid_loginEmail";
    private static final String ERROR_UNDESIRABLE_PASSWORD = "error.undesirable_password";
    private static final CommandResult MAIN_REG = new CommandResult("do/main", true);
    private static final CommandResult MAIN_REG_FAILED = new CommandResult("main");
    private static final String PARAM_LOGIN_EMAIL = "loginEmail";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_RE_PASSWORD = "rePassword";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String ATTR_USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = LocaleUtil.getSessionLocale(request);
        ResourceBundle RB = ResourceBundle.getBundle(RB_NAME, locale);
        String busy_login_err_msg = RB.getString(ERROR_BUSY_LOGIN);
        String pass_mis_err_msg = RB.getString(ERROR_PASS_MISMATCH);
        String invalid_loginEmail_err_msg = RB.getString(ERROR_INVALID_LOGINEMAIL);
        String undesirable_pass_err_msg = RB.getString(ERROR_UNDESIRABLE_PASSWORD);

        CommandResult result;
        String loginEmail = request.getParameter(PARAM_LOGIN_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        String rePassword = request.getParameter(PARAM_RE_PASSWORD);
        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);
        UserDao userDao = new UserDao();
        User user;

        boolean loginEmailValidResult;
        boolean passwordValidResult;
        loginEmailValidResult = Validator.loginEmailValidator(loginEmail);
        passwordValidResult = Validator.passwordValidator(password);
        try {
            if (!loginEmailValidResult) {
                LOGGER.info("Invalid login(email address)");
                session.setAttribute(ERROR, invalid_loginEmail_err_msg);
                result = MAIN_REG_FAILED;
            }else if(!passwordValidResult){
                LOGGER.info("Invalid login(email address)");
                session.setAttribute(ERROR, undesirable_pass_err_msg);
                result = MAIN_REG_FAILED;
            }
            else if (userDao.getByLogin(loginEmail) != null) {
                LOGGER.info("Entered login is busy");
                session.setAttribute(ERROR, busy_login_err_msg);
                result = MAIN_REG_FAILED;
            } else if (!password.equals(rePassword)) {
                LOGGER.info("Password mismatching");
                session.setAttribute(ERROR, pass_mis_err_msg);
                result = MAIN_REG_FAILED;
            } else {
                user = createUser(loginEmail, password, firstname, lastname, userDao);
                LOGGER.info("Newly registered user is: " + user.toString());
                session.setAttribute(ATTR_USER, user);
                result = MAIN_REG;
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in SignUpCommand", e);
            throw new CommandException(e);
        }

        return result;
    }

    private User createUser(String loginEmail, String password, String firstname, String lastname, UserDao userDao) throws DaoException {
        User user;
        user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setLoginEmail(loginEmail);
        user.setPassword(password);
        user.setRole(User.Role.CLIENT);
        user.setWallet(0);
        user.setState(User.State.ENABLED);
        userDao.add(user);
        return user;
    }

}
