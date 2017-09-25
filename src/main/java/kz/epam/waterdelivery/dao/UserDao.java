package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.User;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //create
    void add(User user) throws SQLException;

    //read
    List<User> getAll() throws SQLException;
    User getByLogin(String loginEmail) throws SQLException;
    User getByPass(String password) throws SQLException;

    //update
    void update(User user) throws SQLException;

    //delete
    void remove(User user) throws SQLException;;
}

