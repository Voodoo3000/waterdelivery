package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.OrderContent;

import java.sql.SQLException;
import java.util.List;

public interface OrderContentDAO {
    //create
    void add(OrderContent content) throws SQLException;

    //read
    List<OrderContent> getAll() throws SQLException;
    OrderContent getById(int bottleSize) throws SQLException;

    //update
    void update(OrderContent content) throws SQLException;

    //delete
    void remove(OrderContent content) throws SQLException;

}
