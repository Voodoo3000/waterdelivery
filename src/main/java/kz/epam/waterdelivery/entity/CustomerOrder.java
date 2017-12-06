package kz.epam.waterdelivery.entity;

import kz.epam.waterdelivery.command.CommandException;
import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.sql.OrderContentDao;
import kz.epam.waterdelivery.dao.sql.UserDao;
import org.apache.log4j.Logger;
import java.util.Date;
import java.util.List;

public class CustomerOrder extends Entity {
    private int customerId;
    private double amount;
    private Date orderDate;
    private String address;
    private Status status;
    private User customer;
    private List<OrderContent> contentList;
    private static final Logger LOGGER = Logger.getLogger(CustomerOrder.class);

    public enum Status {
        CREATING, PREPARATION, DELIVERED, CANCELLED
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public java.sql.Date getCurrentDate() {
        java.util.Date orderDate = new java.util.Date();
        return new java.sql.Date(orderDate.getTime());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getCustomer() throws CommandException {
        UserDao userDao = new UserDao();
        try {
            customer = userDao.getById(customerId);
        } catch (DaoException e) {
            LOGGER.error("DaoException in CustomerOrder", e);
            throw new CommandException(e);
        }
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderContent> getContentList() throws CommandException {
        OrderContentDao contentDao = new OrderContentDao();
        try {
            contentList = contentDao.getAllByCustomerOrderId(getId());
        } catch (DaoException e) {
            LOGGER.error("DaoException in CustomerOrder", e);
            throw new CommandException(e);
        }
        return contentList;
    }

    public void setContentList(List<OrderContent> contentList) {
        this.contentList = contentList;
    }
}
