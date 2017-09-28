package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao <T extends Entity> {

    //create
    void add(T entity) throws SQLException;

    //read
    List<T> getAll() throws SQLException;
    T getById(int id) throws SQLException;

    //update
    void update(T entity) throws SQLException;

    //delete
    void remove(T entity) throws SQLException;

}
