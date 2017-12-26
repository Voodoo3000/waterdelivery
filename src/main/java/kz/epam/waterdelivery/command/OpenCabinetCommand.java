package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenCabinetCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(OpenCabinetCommand.class);
    private static final CommandResult RESULT = new CommandResult("customer_cabinet");
    private static final String ATTR_USER = "user";
    private static final String ATTR_ORDER_LIST = "orderList";
    private CustomerOrderDao orderDao = new CustomerOrderDao();

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {

        User user = (User) request.getSession().getAttribute(ATTR_USER);
        List<CustomerOrder> orderList;
        try {
            orderList = orderDao.getAllByClientId(user.getId());
        } catch (DaoException e) {
            LOGGER.error("DaoException in OpenCabinetCommand", e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(ATTR_ORDER_LIST, orderList);

        return RESULT;
    }
}