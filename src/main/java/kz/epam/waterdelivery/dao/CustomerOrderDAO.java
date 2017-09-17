package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.CustomerOrder;

import java.sql.SQLException;
import java.util.List;

public interface CustomerOrderDAO {
    //create
    void add(CustomerOrder order) throws SQLException;

    //read
    List<CustomerOrder> getAll() throws SQLException;
    CustomerOrder getById(int orderId) throws SQLException;

    //update
    void update(CustomerOrder order) throws SQLException;

    //delete
    void remove(CustomerOrder order) throws SQLException;
}
