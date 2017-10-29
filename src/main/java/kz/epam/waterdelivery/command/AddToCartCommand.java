package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.BottleSizeDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.dao.sql.WaterDao;
import kz.epam.waterdelivery.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddToCartCommand implements Command {
    private static final CommandResult CART_ADDING = new CommandResult("do/authorized", true);
    private String waterType;
    private double bottleSize;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        WaterDao waterDao = new WaterDao();
        waterType = request.getParameter("type");
        System.out.println(waterType);
        Water water = waterDao.getByType(waterType);
        System.out.println(water.getPricePerLiter());

        System.out.println(request.getParameter("size"));
        BottleSizeDao bottleSizeDao = new BottleSizeDao();
        bottleSize = Double.parseDouble(request.getParameter("size"));
        BottleSize bottle = bottleSizeDao.getBySize(bottleSize);

        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);

        CustomerOrderDao orderDao = new CustomerOrderDao();
        CustomerOrder order = orderDao.getUnpaidOrderByUserId(user.getId());
        if (order == null) {
            order = new CustomerOrder();
            order.setCustomerId(user.getId());
            orderDao.add(order);
            System.out.println(order);
        }

        OrderContent content = new OrderContent();
        OrderContentDao contentDao = new OrderContentDao();
        content.setWater(water);
        content.setBottleSize(bottle);
        content.setQuantity(Integer.parseInt(request.getParameter("count")));
        content.setCustomerOrderId(orderDao.getByUserId(user.getId()));
        contentDao.add(content);

        request.getSession().setAttribute("order", order);

        return CART_ADDING;
    }
}
