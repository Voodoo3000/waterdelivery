package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserImpl;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpCommand implements Command {
    private CommandResult result;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        System.out.println(firstname);
        HttpSession session = request.getSession();
        UserImpl userImpl = new UserImpl();
        User user = new User();

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setLoginEmail(email);
        user.setPassword(password);
        user.setRole(User.Role.CLIENT);
        user.setWallet(7000);
        try {
            userImpl.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user!=null) result=new CommandResult("main_authorized");

        return result;
    }
}
