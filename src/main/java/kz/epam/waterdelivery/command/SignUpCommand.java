package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpCommand implements Command {
    private static final CommandResult AUTHORIZED = new CommandResult("do/authorized", true);


    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        CommandResult result = null;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        System.out.println(firstname);
        UserDao userDao = new UserDao();
        User user = new User();

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setLoginEmail(email);
        user.setPassword(password);
        user.setRole(User.Role.CLIENT);
        user.setWallet(7000);
        try {
            userDao.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) result = AUTHORIZED;
        request.getSession().setAttribute("user", user);
        return result;
    }
}
