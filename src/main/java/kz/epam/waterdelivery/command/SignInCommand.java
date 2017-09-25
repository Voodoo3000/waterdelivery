package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserImpl;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class SignInCommand implements Command {

    private CommandResult result;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);

        UserImpl userImpl = new UserImpl();
        try {
            User userFoundByLogin = userImpl.getByLogin(email);
            if (userFoundByLogin != null && userFoundByLogin.getPassword().equals(password)) {
                result=new CommandResult("main_authorized");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
