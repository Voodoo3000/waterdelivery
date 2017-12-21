package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.entity.OrderContent;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RemoveContentCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(RemoveContentCommand.class);
    private static final CommandResult RESULT = new CommandResult("do/open_customer_cart", true);
    ;
    private int contentPositionId;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        contentPositionId = Integer.parseInt(request.getParameter("id"));
        OrderContentDao contentDao = new OrderContentDao();
        OrderContent content = new OrderContent();
        content.setId(contentPositionId);
        try {
            contentDao.remove(content);
            LOGGER.info("Content was removed");
        } catch (DaoException e) {
            LOGGER.error("DaoException in RemoveContentCommand", e);
            throw new CommandException(e);
        }
        return RESULT;
    }
}
