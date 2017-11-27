package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class ChangeUserCommand implements Command {

    private static final CommandResult RESULT =  new CommandResult("do/main", true);
    private static final String PARAM_FIRSTNAME = "firstName";
    private static final String PARAM_LASTNAME = "lastName";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_ID = "id";
    private static final String PARAM_ROLE = "role";
    private static final String PARAM_WALLET = "wallet";
    private static final String PARAM_STATE = "state";
    private static final String ATTR_USER_LIST = "userList";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);
        String loginEmail = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        Integer id = Integer.parseInt(request.getParameter(PARAM_ID));
        User.Role role = User.Role.valueOf(request.getParameter(PARAM_ROLE));
        Double wallet = Double.valueOf(request.getParameter(PARAM_WALLET));
        User.State state = User.State.valueOf(request.getParameter(PARAM_STATE));

        UserDao userDao = new UserDao();
        User user = userDao.getById(id);

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setLoginEmail(loginEmail);
        user.setPassword(password);
        user.setId(id);
        user.setRole(role);
        user.setWallet(wallet);
        user.setState(state);
        userDao.update(user);

        List<User> userList = userDao.getAll();
        request.getSession().setAttribute(ATTR_USER_LIST, userList);

        return RESULT;
    }
}
