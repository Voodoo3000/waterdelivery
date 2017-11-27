package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.*;
import kz.epam.waterdelivery.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class OpenAdminPageCommand implements Command {

    private CommandResult result;
    private static final String ATTR_USER_LIST = "userList";
    private static final String ATTR_ORDER_LIST = "orderList";
    private static final String ATTR_CONTENT_LIST = "contentList";
    private static final String ATTR_WATER_LIST = "waterList";
    private static final String ATTR_BOTTLESIZE_LIST = "bottleSizeList";

    public OpenAdminPageCommand(String page) {
        result = new CommandResult(page);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        OrderContentDao contentDao = new OrderContentDao();
        CustomerOrderDao orderDao = new CustomerOrderDao();
        UserDao userDao = new UserDao();
        WaterDao waterDao = new WaterDao();
        BottleSizeDao bottleSizeDao = new BottleSizeDao();

        List<User> userList = userDao.getAll();
        List<CustomerOrder> orderList = orderDao.getAll();
        List<OrderContent> contentList = contentDao.getAll();
        List<Water> waterList = waterDao.getAll();
        List<BottleSize> bottleSizeList = bottleSizeDao.getAll();

        request.getSession().setAttribute(ATTR_USER_LIST, userList);
        request.getSession().setAttribute(ATTR_ORDER_LIST, orderList);
        request.getSession().setAttribute(ATTR_CONTENT_LIST, contentList);
        request.getSession().setAttribute(ATTR_WATER_LIST, waterList);
        request.getSession().setAttribute(ATTR_BOTTLESIZE_LIST, bottleSizeList);

        return result;
    }
}
