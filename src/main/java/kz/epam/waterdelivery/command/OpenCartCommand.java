package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerAddressDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.entity.CustomerAddress;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class OpenCartCommand implements Command {

    private static final CommandResult RESULT = new CommandResult("customer_cart");
    private static final String ATTR_USER = "user";
    private static final String ATTR_CONTENT_LIST = "contentList";
    private static final String ATTR_TOTAL_AMOUNT = "totalAmount";
    private static final String ATTR_ADDRESS = "address";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        double totalAmount = 0;
        CustomerOrder order;
        CustomerAddress address;
        CustomerOrderDao orderDao = new CustomerOrderDao();
        OrderContentDao contentDao = new OrderContentDao();
        CustomerAddressDao addressDao = new CustomerAddressDao();

        User user = (User) request.getSession().getAttribute(ATTR_USER);
        order = orderDao.getCreatingOrderByUserId(user.getId());
        if (order != null) {
            List<OrderContent> contentReal = contentDao.getAllByCustomerOrderId(order.getId());
            request.getSession().setAttribute(ATTR_CONTENT_LIST, contentReal);
            for (OrderContent content : contentReal) {
                totalAmount = totalAmount + (content.getWater().getPricePerLiter() * content.getBottleSize().getSize() * content.getQuantity());
            }
            order.setAmount(totalAmount);
            orderDao.update(order);
        }
        address = addressDao.getByCustomerId(user.getId());

        if (address != null) {
            request.getSession().setAttribute(ATTR_ADDRESS, address);
        }
        else {
            address = new CustomerAddress();
            address.setCustomerId(user.getId());
            addressDao.add(address);
        }
        request.getSession().setAttribute(ATTR_TOTAL_AMOUNT, totalAmount);

        return RESULT;
    }
}
