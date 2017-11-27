package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpCommand implements Command {

    private static final CommandResult MAIN_REG = new CommandResult("do/main", true);
    private static final CommandResult MAIN_REG_FAILED = new CommandResult("main");
    private static final String PARAM_LOGIN_EMAIL = "loginEmail";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_RE_PASSWORD = "password1";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String ATTR_USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        CommandResult result;

        String loginEmail = request.getParameter(PARAM_LOGIN_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        String password1 = request.getParameter(PARAM_RE_PASSWORD);
        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);
        UserDao userDao = new UserDao();
        User user;

        if(userDao.getByLogin(loginEmail) != null) {
            System.out.println("BUSY LOGIN");
            result = MAIN_REG_FAILED;
        }

        else if(!password.equals(password1)) {
            System.out.println("MISMATCH ENTERED PASSWORDS");
            result = MAIN_REG_FAILED;
        }
        else {
            user = new User();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setLoginEmail(loginEmail);
            user.setPassword(password);
            user.setRole(User.Role.CLIENT);
            user.setWallet(0);
            user.setState(User.State.ENABLED);
            try {
                userDao.add(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute(ATTR_USER, user);
            result = MAIN_REG;
        }

        return result;
    }
}
