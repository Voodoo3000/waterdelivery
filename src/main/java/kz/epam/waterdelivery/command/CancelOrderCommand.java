package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CancelOrderCommand implements Command {

    private static final String PARAM_ID = "id";
    private static final String ATTR_USER = "user";
    private CommandResult RESULT =  new CommandResult("do/open_cabinet", true);

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        int contentPositionId = Integer.parseInt(request.getParameter(PARAM_ID));

        UserDao userDao = new UserDao();
        User user;

        CustomerOrderDao orderDao = new CustomerOrderDao();
        CustomerOrder order;
        order = orderDao.getById(contentPositionId);

        user = userDao.getById(order.getCustomerId());
        user.setWallet(user.getWallet()+order.getAmount());
        userDao.update(user);

        order.setAmount(order.getAmount()-order.getAmount());
        order.setStatus(CustomerOrder.Status.CANCELLED);
        orderDao.update(order);

        request.getSession().setAttribute(ATTR_USER, user);

        return RESULT;
    }
}
