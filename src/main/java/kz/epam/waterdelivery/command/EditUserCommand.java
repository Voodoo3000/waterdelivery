package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EditUserCommand implements Command {

    private static final CommandResult RESULT = new CommandResult("customer_cabinet");
    private static final String PARAM_LOGIN_EMAIL = "loginEmail";
    private static final String PARAM_OLD_PASSWORD = "oldPassword";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    private static final String PARAM_RE_NEW_PASSWORD = "newRePassword";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_LASTNAME = "lastname";
    private static final String ATTR_USER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        String loginEmail = request.getParameter(PARAM_LOGIN_EMAIL);
        String oldPassword = request.getParameter(PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        String newRePassword = request.getParameter(PARAM_RE_NEW_PASSWORD);
        String firstname = request.getParameter(PARAM_FIRSTNAME);
        String lastname = request.getParameter(PARAM_LASTNAME);

        UserDao userDao = new UserDao();
        User user = userDao.getByLogin(loginEmail);

        if (!oldPassword.equals(user.getPassword())) {
            System.out.println("WRONG PASSWORD");
        }

        else if(!newPassword.equals(newRePassword)) {
            System.out.println("MISMATCH ENTERED PASSWORDS");
        }
        else {
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setPassword(newPassword);
            userDao.update(user);

            request.getSession().setAttribute(ATTR_USER, user);
        }
        return RESULT;
    }
}
