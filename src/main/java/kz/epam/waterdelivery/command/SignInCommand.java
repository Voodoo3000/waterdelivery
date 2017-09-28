package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.UserImpl;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignInCommand implements Command {

    private CommandResult result;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);

        UserImpl userImpl = new UserImpl();
        User user = null;
        try {
            user = userImpl.getByLogin(email);
            if (user != null && user.getPassword().equals(password)) {
                result=new CommandResult("main_authorized");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("user", user);
        return result;
    }
}
