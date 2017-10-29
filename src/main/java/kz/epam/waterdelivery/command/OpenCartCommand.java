package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class OpenCartCommand implements Command {

    private CommandResult result;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        double totalAmount = 0;
        CustomerOrderDao orderDao = new CustomerOrderDao();
        OrderContentDao contentDao = new OrderContentDao();
        CustomerOrder order;

        User user = (User) request.getSession().getAttribute("user");
        order = orderDao.getUnpaidOrderByUserId(user.getId());
        List<OrderContent> contentReal = contentDao.getByCustomerOrderId(order.getId());
        for (OrderContent content : contentReal) {
            totalAmount = totalAmount + (content.getWater().getPricePerLiter() * content.getBottleSize().getSize() * content.getQuantity());
        }
        order.setAmount(totalAmount);
        orderDao.update(order);

        request.getSession().setAttribute("totalAmount", totalAmount);
        request.getSession().setAttribute("contentList", contentReal);

        result = new CommandResult("customer_cart");
        return result;
    }
}
