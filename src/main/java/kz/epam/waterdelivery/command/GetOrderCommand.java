package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.CustomerAddressDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerAddress;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class GetOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(GetOrderCommand.class);
    private static final String PARAM_ADDRESS_CITY = "city";
    private static final String PARAM_ADDRESS_STREET = "street";
    private static final String PARAM_ADDRESS_HOUSE = "house";
    private static final String PARAM_ADDRESS_APARTMENT = "apartment";
    private static final String PARAM_ADDRESS_PHONE = "phone";
    private static final String ATTR_USER = "user";
    private static final String ATTR_CONTENT_LIST = "contentList";
    private static final String ATTR_TOTAL_AMOUNT = "totalAmount";

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {

        double charge;
        CommandResult result;
        CustomerOrder order;
        CustomerAddress address;
        UserDao userDao = new UserDao();
        CustomerOrderDao orderDao = new CustomerOrderDao();
        CustomerAddressDao addressDao = new CustomerAddressDao();
        Date orderDate = new Date();

        String city = request.getParameter(PARAM_ADDRESS_CITY);
        String street = request.getParameter(PARAM_ADDRESS_STREET);
        String house = request.getParameter(PARAM_ADDRESS_HOUSE);
        String apartment = request.getParameter(PARAM_ADDRESS_APARTMENT);
        String phone = request.getParameter(PARAM_ADDRESS_PHONE);

        User user = (User) request.getSession().getAttribute(ATTR_USER);
        try {
            order = orderDao.getCreatingOrderByUserId(user.getId());
            if (order.getAmount() <= user.getWallet()) {
                order.setOrderDate(orderDate);
                order.setStatus(CustomerOrder.Status.PREPARATION);
                orderDao.update(order);
                LOGGER.info("Customer ordered water delivering");
                charge = user.getWallet() - order.getAmount();
                user.setWallet(charge);
                userDao.update(user);
                LOGGER.info("Order price was charged from customer wallet");
                address = addressDao.getByCustomerId(user.getId());
                address.setCity(city);
                address.setStreet(street);
                address.setHouseNumber(house);
                address.setApartmentNumber(apartment);
                address.setPhoneNumber(phone);
                addressDao.update(address);
                LOGGER.info("Customer address was filled");
                request.getSession().removeAttribute(ATTR_CONTENT_LIST);
                request.getSession().removeAttribute(ATTR_TOTAL_AMOUNT);
                result = new CommandResult("completed_order");
            } else {
                result = new CommandResult("customer_cart");
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in GetOrderCommand", e);
            throw new CommandException(e);
        }
        return result;
    }
}
