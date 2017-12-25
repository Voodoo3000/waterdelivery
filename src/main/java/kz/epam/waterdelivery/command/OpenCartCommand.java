package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.CustomerAddressDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OpenCartCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(OpenCartCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {

        double totalAmount = 0;
        CustomerOrder order;
        CustomerAddress address;
        CustomerOrderDao orderDao = new CustomerOrderDao();
        OrderContentDao contentDao = new OrderContentDao();
        CustomerAddressDao addressDao = new CustomerAddressDao();

        User user = (User) request.getSession().getAttribute(Entity.ATTR_USER);
        try {
            order = orderDao.getCreatingOrderByUserId(user.getId());
            LOGGER.info("Getting current order");
            if (order != null) {
                List<OrderContent> contentReal;
                contentReal = contentDao.getAllByCustomerOrderId(order.getId());
                request.getSession().setAttribute(Entity.ATTR_CONTENT_LIST, contentReal);
                for (OrderContent content : contentReal) {
                    totalAmount = totalAmount + (content.getWater().getPricePerLiter() * content.getBottleSize().getSize() * content.getQuantity());
                }
                order.setAmount(totalAmount);
                orderDao.update(order);
                LOGGER.info("Order price was calculated");
            }
            address = addressDao.getByCustomerId(user.getId());
            if (address != null) {
                request.getSession().setAttribute(Entity.ATTR_ADDRESS, address);
            } else {
                address = new CustomerAddress();
                address.setCustomerId(user.getId());
                addressDao.add(address);
                LOGGER.info("Address was created");
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in OpenCartCommand", e);
            throw new CommandException(e);
        }
        request.getSession().setAttribute(Entity.PARAM_TOTAL_AMOUNT, totalAmount);

        return Entity.CUSTOMER_CART;
    }
}
