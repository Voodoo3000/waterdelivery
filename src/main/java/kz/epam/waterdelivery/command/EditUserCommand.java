package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditUserCommand.class);
    private static final CommandResult RESULT = new CommandResult("customer_cabinet");
    private static final String PARAM_LOGIN_EMAIL = "loginEmail";
    private static final String PARAM_OLD_PASSWORD = "oldPassword";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    private static final String PARAM_RE_NEW_PASSWORD = "newRePassword";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String ATTR_USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {

        String loginEmail = request.getParameter(PARAM_LOGIN_EMAIL);
        String oldPassword = request.getParameter(PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        String newRePassword = request.getParameter(PARAM_RE_NEW_PASSWORD);
        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);

        UserDao userDao = new UserDao();
        User user;
        try {
            user = userDao.getByLogin(loginEmail);
            if (!oldPassword.equals(user.getPassword())) {
                LOGGER.info("Wrong password");
            } else if (!newPassword.equals(newRePassword)) {
                LOGGER.info("Entered password mismatching");
            } else {
                user.setFirstName(firstname);
                user.setLastName(lastname);
                user.setPassword(newPassword);
                userDao.update(user);
                LOGGER.info("User " + user.getLoginEmail() + " was changed by him self");
                request.getSession().setAttribute(ATTR_USER, user);
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in EditUserCommand", e);
            throw new CommandException(e);
        }
        return RESULT;
    }
}
