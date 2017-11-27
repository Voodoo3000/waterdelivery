package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.sql.CustomerAddressDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerAddress;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class GetOrderCommand implements Command {

    private static final String PARAM_ADDRESS_CITY = "city";
    private static final String PARAM_ADDRESS_STREET = "street";
    private static final String PARAM_ADDRESS_HOUSE = "house";
    private static final String PARAM_ADDRESS_APARTMENT = "apartment";
    private static final String PARAM_ADDRESS_PHONE = "phone";
    private static final String ATTR_USER = "user";
    private static final String ATTR_CONTENT_LIST = "contentList";
    private static final String ATTR_TOTAL_AMOUNT = "totalAmount";
    private CommandResult result;
    private double charge;

    @Override
    public CommandResult execute(HttpServletRequest request) throws IOException {

        CustomerOrder order;
        UserDao userDao = new UserDao();
        CustomerAddress address;
        CustomerOrderDao orderDao = new CustomerOrderDao();
        CustomerAddressDao addressDao = new CustomerAddressDao();
        Date orderDate = new Date();

        String city = request.getParameter(PARAM_ADDRESS_CITY);
        String street = request.getParameter(PARAM_ADDRESS_STREET);
        String house = request.getParameter(PARAM_ADDRESS_HOUSE);
        String apartment = request.getParameter(PARAM_ADDRESS_APARTMENT);
        String phone = request.getParameter(PARAM_ADDRESS_PHONE);

        User user = (User) request.getSession().getAttribute(ATTR_USER);
        order = orderDao.getCreatingOrderByUserId(user.getId());

        if (order.getAmount() <= user.getWallet()) {
            order.setOrderDate(orderDate);
            order.setStatus(CustomerOrder.Status.PREPARATION);
            orderDao.update(order);

            charge = user.getWallet() - order.getAmount();
            user.setWallet(charge);
            userDao.update(user);

            address = addressDao.getByCustomerId(user.getId());
            address.setCity(city);
            address.setStreet(street);
            address.setHouseNumber(house);
            address.setApartmentNumber(apartment);
            address.setPhoneNumber(phone);
            addressDao.update(address);

            request.getSession().removeAttribute(ATTR_CONTENT_LIST);
            request.getSession().removeAttribute(ATTR_TOTAL_AMOUNT);

            result = new CommandResult("completed_order");;
        } else {
            result = new CommandResult("customer_cart");;
        }
        return result;
    }
}
