package kz.epam.waterdelivery.command;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.CustomerAddressDao;
import kz.epam.waterdelivery.dao.sql.CustomerOrderDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import kz.epam.waterdelivery.entity.CustomerAddress;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.entity.Entity;
import kz.epam.waterdelivery.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class GetOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(GetOrderCommand.class);

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

        String city = request.getParameter(Entity.PARAM_ADDRESS_CITY);
        String street = request.getParameter(Entity.PARAM_ADDRESS_STREET);
        String house = request.getParameter(Entity.PARAM_ADDRESS_HOUSE);
        String apartment = request.getParameter(Entity.PARAM_ADDRESS_APARTMENT);
        String phone = request.getParameter(Entity.PARAM_ADDRESS_PHONE);

        User user = (User) request.getSession().getAttribute(Entity.ATTR_USER);
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
                request.getSession().removeAttribute(Entity.ATTR_CONTENT_LIST);
                request.getSession().removeAttribute(Entity.PARAM_TOTAL_AMOUNT);
                result = new CommandResult(Entity.COMPLETED_ORDER);
            } else {
                result = new CommandResult(Entity.CUSTOMER_CART_PAGE);
            }
        } catch (DaoException e) {
            LOGGER.error("DaoException in GetOrderCommand", e);
            throw new CommandException(e);
        }
        return result;
    }
}
