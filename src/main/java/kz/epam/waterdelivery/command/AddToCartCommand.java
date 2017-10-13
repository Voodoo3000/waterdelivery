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


        Water water;
        WaterDao waterDao = new WaterDao();
        waterType = request.getParameter("type");
        System.out.println(waterType);
        water = waterDao.getByType(waterType);
        System.out.println(water.getPricePerLiter());

        BottleSize bottle;
        BottleSizeDao bottleSizeDao = new BottleSizeDao();

        System.out.println(request.getParameter("size"));
        bottleSize = Double.parseDouble(request.getParameter("size"));
        bottle = bottleSizeDao.getBySize(bottleSize);

        User user;
        user = (User) request.getSession().getAttribute("user");
        System.out.println(user);

        CustomerOrderDao orderDao = new CustomerOrderDao();
        CustomerOrder order = (CustomerOrder) request.getSession().getAttribute("order");
        if (order == null) {
            CustomerOrder order1 = new CustomerOrder();
            order1.setCustomerId(user.getId());
            orderDao.add(order1);
            System.out.println(order1);
        }

        OrderContent content = new OrderContent();
        OrderContentDao contentDao = new OrderContentDao();
        content.setWaterId(water.getId());
        content.setBottleSizeId(bottle.getId());
        content.setQuantity(Integer.parseInt(request.getParameter("count")));
        content.setPrice(content.getQuantity()*(water.getPricePerLiter()*bottle.getSize()));
        content.setCustomerOrderId(orderDao.getByUserId(user.getId()));
        contentDao.add(content);

        order = orderDao.getById(content.getCustomerOrderId());
        order.setAmount(content.getPrice() + order.getAmount());
        order.setAddress("Metallurgov 2 apt 68");
        orderDao.update(order);
        request.getSession().setAttribute("order", order);
        request.getSession().setAttribute("content", content);
        water = waterDao.getById(content.getWaterId());
        bottle = bottleSizeDao.getById(content.getBottleSizeId());
        request.getSession().setAttribute("water", water);
        request.getSession().setAttribute("bottle", bottle);


        return CART_ADDING;
    }
}
