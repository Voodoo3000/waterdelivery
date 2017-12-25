package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeUserCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {

        String firstName = request.getParameter(Entity.PARAM_FIRSTNAME);
        String lastName = request.getParameter(Entity.PARAM_LASTNAME);
        String loginEmail = request.getParameter(Entity.PARAM_LOGIN_EMAIL);
        String currentPassword = request.getParameter(Entity.PARAM_CURRENT_PASSWORD);
        Integer id = Integer.parseInt(request.getParameter(Entity.PARAM_ID));
        User.Role role = User.Role.valueOf(request.getParameter(Entity.PARAM_ROLE));
        Double wallet = Double.valueOf(request.getParameter(Entity.PARAM_WALLET));
        User.State state = User.State.valueOf(request.getParameter(Entity.PARAM_STATE));
        UserDao userDao = new UserDao();
        User user;
        try {
            user = userDao.getById(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLoginEmail(loginEmail);
            user.setPassword(currentPassword);
            user.setId(id);
            user.setRole(role);
            user.setWallet(wallet);
            user.setState(state);
            userDao.update(user);
            LOGGER.info("User " + user + " was changed by administrator");
            List<User> userList;
            userList = userDao.getAll();
            request.getSession().setAttribute(Entity.ATTR_USER_LIST, userList);
        } catch (DaoException e) {
            LOGGER.error("DaoException in ChangeUserCommand", e);
            throw new CommandException(e);
        }
        return Entity.MAIN;
    }
}
