package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    //create
    void add(User user) throws SQLException;

    //read
    List<User> getAll() throws SQLException;

    User getById(int userId) throws SQLException;

    //update
    void update(User user) throws SQLException;

    //delete
    void remove(User user) throws SQLException;
}

