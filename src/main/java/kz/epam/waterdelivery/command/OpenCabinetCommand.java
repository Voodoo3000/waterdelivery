package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenCabinetCommand implements Command {

    private static final CommandResult RESULT = new CommandResult("customer_cabinet");
    private CustomerOrderDao orderDao = new CustomerOrderDao();
    private static final String ATTR_USER = "user";
    private static final String ATTR_ORDER_LIST = "orderList";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        User user = (User) request.getSession().getAttribute(ATTR_USER);
        List<CustomerOrder> orderList = orderDao.getAllByClientId(user.getId());

        request.getSession().setAttribute(ATTR_ORDER_LIST, orderList);

        return RESULT;
    }
}