package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SignOutCommand.class);
    private static final CommandResult RESULT = new CommandResult("do/main", true);
    private static final String ATTR_USER = "user";
    private static final String ATTR_CONTENT_LIST = "contentList";
    private static final String ATTR_ORDER = "order";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute(ATTR_USER);
        if (user == null) return RESULT;

        session.removeAttribute(ATTR_CONTENT_LIST);
        session.removeAttribute(ATTR_USER);
        session.removeAttribute(ATTR_ORDER);
        LOGGER.info("User signed out, session closed");

        return RESULT;
    }
}
