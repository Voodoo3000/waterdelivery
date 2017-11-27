package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class ChangeOrderCommand implements Command {

    private static final CommandResult RESULT = new CommandResult("do/admin_orders", true);
    private static final String PARAM_ID = "id";
    private static final String PARAM_STATUS = "status";
    private static final String ATTR_ORDER_LIST = "orderList";

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        int contentPositionId = Integer.parseInt(request.getParameter(PARAM_ID));

        CustomerOrder order;
        CustomerOrderDao orderDao = new CustomerOrderDao();
        order = orderDao.getById(contentPositionId);

        CustomerOrder.Status status = CustomerOrder.Status.valueOf(request.getParameter(PARAM_STATUS));
        order.setStatus(status);
        orderDao.update(order);

        List<CustomerOrder> orderList = orderDao.getAll();
        request.getSession().setAttribute(ATTR_ORDER_LIST, orderList);

        return RESULT;
    }
}
