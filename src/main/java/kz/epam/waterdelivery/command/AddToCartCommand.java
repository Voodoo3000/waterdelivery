package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddToCartCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddToCartCommand.class);
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_COUNT = "count";
    private static final String ATTR_USER = "user";
    private static final String ATTR_ORDER = "order";
    private static final CommandResult CART_ADDING = new CommandResult("do/main", true);

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String waterType;
        double bottleSize;

        Water water;
        BottleSize bottle;
        CustomerOrder order;

        OrderContent content = new OrderContent();
        WaterDao waterDao = new WaterDao();
        BottleSizeDao bottleSizeDao = new BottleSizeDao();
        CustomerOrderDao orderDao = new CustomerOrderDao();
        OrderContentDao contentDao = new OrderContentDao();

        waterType = request.getParameter(PARAM_TYPE);
        bottleSize = Double.parseDouble(request.getParameter(PARAM_SIZE));
        User user = (User) request.getSession().getAttribute(ATTR_USER);
        try {
            water = waterDao.getByType(waterType);
            bottle = bottleSizeDao.getBySize(bottleSize);
            order = orderDao.getCreatingOrderByUserId(user.getId());
            if (order == null) {
                order = new CustomerOrder();
                order.setCustomerId(user.getId());
                order.setStatus(CustomerOrder.Status.CREATING);
                orderDao.add(order);
                LOGGER.info("Order was created");
            }
            content.setWater(water);
            content.setBottleSize(bottle);
            content.setQuantity(Integer.parseInt(request.getParameter(PARAM_COUNT)));
            content.setCustomerOrderId(orderDao.getByUserId(user.getId()));
            contentDao.add(content);
            LOGGER.info("Content was created");
            request.getSession().setAttribute(ATTR_ORDER, order);
        } catch (DaoException e) {
            LOGGER.error("DaoException in AddToCartCommand", e);
            throw new CommandException(e);
        }
        return CART_ADDING;
    }
}
