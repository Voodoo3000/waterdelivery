package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenAdminPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(OpenAdminPageCommand.class);
    private static final String ATTR_USER_LIST = "userList";
    private static final String ATTR_ORDER_LIST = "orderList";
    private static final String ATTR_CONTENT_LIST = "contentList";
    private CommandResult result;

    public OpenAdminPageCommand(String page) {
        result = new CommandResult(page);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {

        OrderContentDao contentDao = new OrderContentDao();
        CustomerOrderDao orderDao = new CustomerOrderDao();
        UserDao userDao = new UserDao();

        List<User> userList;
        List<CustomerOrder> orderList;
        List<OrderContent> contentList;
        try {
            userList = userDao.getAll();
            orderList = orderDao.getAll();
            contentList = contentDao.getAll();
        } catch (DaoException e) {
            LOGGER.error("DaoException in OpenAdminPageCommand", e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ATTR_USER_LIST, userList);
        request.getSession().setAttribute(ATTR_ORDER_LIST, orderList);
        request.getSession().setAttribute(ATTR_CONTENT_LIST, contentList);
        return result;
    }
}
