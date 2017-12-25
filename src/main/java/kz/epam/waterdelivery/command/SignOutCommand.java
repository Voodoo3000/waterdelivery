package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SignOutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute(Entity.ATTR_USER);
        if (user == null) return Entity.MAIN;

        session.removeAttribute(Entity.ATTR_CONTENT_LIST);
        session.removeAttribute(Entity.ATTR_USER);
        session.removeAttribute(Entity.ATTR_ORDER);
        LOGGER.info("User signed out, session closed");

        return Entity.MAIN;
    }
}
