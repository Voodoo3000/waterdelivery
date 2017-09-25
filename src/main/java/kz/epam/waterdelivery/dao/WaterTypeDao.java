package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.WaterType;

import java.sql.SQLException;
import java.util.List;

public interface WaterTypeDao {
    //create
    void add(WaterType waterType) throws SQLException;

    //read
    List<WaterType> getAll() throws SQLException;
    WaterType getById(int waterTypeId) throws SQLException;

    //update
    void update(WaterType waterType) throws SQLException;

    //delete
    void remove(WaterType waterType) throws SQLException;
}
