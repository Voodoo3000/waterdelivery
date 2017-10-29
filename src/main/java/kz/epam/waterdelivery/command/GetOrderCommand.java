package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class GetOrderCommand implements Command {

    private static final CommandResult AUTHORIZED = new CommandResult("do/authorized", true);
    private static final CommandResult CART = new CommandResult("do/customer_cart", true);
    private CommandResult result;
    private double charge;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        CustomerOrder order;
        UserDao userDao = new UserDao();
        CustomerOrderDao orderDao = new CustomerOrderDao();
        Date orderDate = new Date();

        String address = request.getParameter("address");

        User user = (User) request.getSession().getAttribute("user");
        order = orderDao.getUnpaidOrderByUserId(user.getId());

        if (order.getAmount() <= user.getWallet() ) {
            order.setPayment(true);
            order.setOrderDate(orderDate);
            order.setAddress(address);
            orderDao.update(order);

            charge = user.getWallet() - order.getAmount();
            user.setWallet(charge);
            userDao.update(user);

            request.getSession().removeAttribute("contentList");
            request.getSession().removeAttribute("totalAmount");

            result = new CommandResult("main_authorized");;
        } else {
            result = new CommandResult("customer_cart");;
        }
        return result;
    }
}
