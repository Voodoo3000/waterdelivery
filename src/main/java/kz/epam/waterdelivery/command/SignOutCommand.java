package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements Command {

    private static final CommandResult RESULT = new CommandResult("do/main", true);

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return RESULT;

        session.removeAttribute("contentList");
        session.removeAttribute("user");
        session.removeAttribute("order");

        return RESULT;
    }
}
