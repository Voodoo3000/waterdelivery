package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ChangeOrderCommand.class);
    private static final CommandResult RESULT = new CommandResult("do/admin_orders", true);
    private static final String PARAM_ID = "id";
    private static final String PARAM_STATUS = "status";
    private static final String ATTR_ORDER_LIST = "orderList";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        CustomerOrder.Status status = CustomerOrder.Status.valueOf(request.getParameter(PARAM_STATUS));
        int contentPositionId = Integer.parseInt(request.getParameter(PARAM_ID));
        List<CustomerOrder> orderList;
        CustomerOrder order;
        CustomerOrderDao orderDao = new CustomerOrderDao();
        try {
            order = orderDao.getById(contentPositionId);
            order.setStatus(status);
            orderDao.update(order);
            LOGGER.info("Status of order " + order + " was changed " + "to " + status + " by administrator");
            orderList = orderDao.getAll();
            request.getSession().setAttribute(ATTR_ORDER_LIST, orderList);
        } catch (DaoException e) {
            LOGGER.error("DaoException in ChangeOrderCommand", e);
            throw new CommandException(e);
        }
        return RESULT;
    }
}
