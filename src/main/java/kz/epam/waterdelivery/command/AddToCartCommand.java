package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddToCartCommand implements Command {

    private static final String PARAM_TYPE = "type";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_COUNT = "count";
    private static final String ATTR_USER = "user";
    private static final String ATTR_ORDER = "order";
    private static final CommandResult CART_ADDING = new CommandResult("do/main", true);
    private String waterType;
    private double bottleSize;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        WaterDao waterDao = new WaterDao();
        waterType = request.getParameter(PARAM_TYPE);
        Water water = waterDao.getByType(waterType);

        BottleSizeDao bottleSizeDao = new BottleSizeDao();
        bottleSize = Double.parseDouble(request.getParameter(PARAM_SIZE));
        BottleSize bottle = bottleSizeDao.getBySize(bottleSize);

        User user = (User) request.getSession().getAttribute(ATTR_USER);

        CustomerOrderDao orderDao = new CustomerOrderDao();
        CustomerOrder order = orderDao.getCreatingOrderByUserId(user.getId());
        if (order == null) {
            order = new CustomerOrder();
            order.setCustomerId(user.getId());
            order.setStatus(CustomerOrder.Status.CREATING);
            orderDao.add(order);
        }

        OrderContent content = new OrderContent();
        OrderContentDao contentDao = new OrderContentDao();
        content.setWater(water);
        content.setBottleSize(bottle);
        content.setQuantity(Integer.parseInt(request.getParameter(PARAM_COUNT)));
        content.setCustomerOrderId(orderDao.getByUserId(user.getId()));
        contentDao.add(content);

        request.getSession().setAttribute(ATTR_ORDER, order);

        return CART_ADDING;
    }
}
