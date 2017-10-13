package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.User;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;

public class EditUserCommand implements Command {

    private CommandResult result;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        UserDao userDao = new UserDao();
        User user = (User) request.getSession().getAttribute("user");

        user.setLoginEmail(email);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setPassword(password);

        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        result = new CommandResult("cabinet");
        request.getSession().setAttribute("user", user);
        return result;
    }
}
