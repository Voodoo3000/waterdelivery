package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CancelOrderCommand.class);
    private static final String PARAM_ID = "id";
    private static final String ATTR_USER = "user";
    private CommandResult RESULT = new CommandResult("do/open_cabinet", true);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        User user;
        CustomerOrder order;
        UserDao userDao = new UserDao();
        CustomerOrderDao orderDao = new CustomerOrderDao();
        int contentPositionId = Integer.parseInt(request.getParameter(PARAM_ID));
        try {
            order = orderDao.getById(contentPositionId);
            user = userDao.getById(order.getCustomerId());
            user.setWallet(user.getWallet() + order.getAmount());
            userDao.update(user);
            LOGGER.info("Money was refunded to the customer because of order cancellation");
            order.setAmount(order.getAmount() - order.getAmount());
            order.setStatus(CustomerOrder.Status.CANCELLED);
            orderDao.update(order);
            LOGGER.info("Order was cancelled by customer");
        } catch (DaoException e) {
            LOGGER.error("DaoException in CancelOrderCommand", e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ATTR_USER, user);
        return RESULT;
    }
}
