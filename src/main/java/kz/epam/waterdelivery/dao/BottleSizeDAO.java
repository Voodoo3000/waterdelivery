package kz.epam.waterdelivery.dao;

import kz.epam.waterdelivery.entity.BottleSize;
import java.sql.SQLException;
import java.util.List;

public interface BottleSizeDAO {
    //create
    void add(BottleSize bottle) throws SQLException;

    //read
    List<BottleSize> getAll() throws SQLException;
    BottleSize getById(int bottleId) throws SQLException;

    //update
    void update(BottleSize bottle) throws SQLException;

    //delete
    void remove(BottleSize bottle) throws SQLException;
}
