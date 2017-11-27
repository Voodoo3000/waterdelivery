package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.entity.OrderContent;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RemoveContentCommand implements Command {

    private static final CommandResult RESULT =  new CommandResult("do/open_customer_cart", true);;
    private int contentPositionId;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        contentPositionId = Integer.parseInt(request.getParameter("id"));

        OrderContentDao contentDao = new OrderContentDao();
        OrderContent content = new OrderContent();
        content.setId(contentPositionId);
        contentDao.remove(content);

        return RESULT;
    }
}
