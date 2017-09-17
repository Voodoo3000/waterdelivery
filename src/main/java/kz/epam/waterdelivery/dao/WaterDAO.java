package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.entity.Water;

import java.sql.SQLException;
import java.util.List;

public interface WaterDAO {
    //create
    void add(Water water) throws SQLException;

    //read
    List<Water> getAll() throws SQLException;
    Water getById(int waterId) throws SQLException;

    //update
    void update(Water water) throws SQLException;

    //delete
    void remove(Water water) throws SQLException;
}
