package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BottleSizeDao implements GenericDao<BottleSize> {

    private static final Logger LOGGER = Logger.getLogger(BottleSizeDao.class);
    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(BottleSize bottle) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "INSERT INTO BOTTLE_SIZE (SIZE) VALUES(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, bottle.getSize());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Bottle creating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<BottleSize> getAll() throws DaoException {
        Connection connection = pool.getConnection();
        List<BottleSize> bottleSizeList = new ArrayList<>();
        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                BottleSize bottle = new BottleSize();
                bottle.setId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));
                bottleSizeList.add(bottle);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all bottle SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return bottleSizeList;
    }

    @Override
    public BottleSize getById(int id) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        BottleSize bottle = null;
        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bottle = new BottleSize();
                bottle.setId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get water by id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return bottle;
    }

    public BottleSize getBySize(double size) throws DaoException {
        Connection connection = pool.getConnection();
        BottleSize bottle = null;
        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE WHERE SIZE=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, size);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bottle = new BottleSize();
                bottle.setId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get bottle by size SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return bottle;
    }

    @Override
    public void update(BottleSize bottle) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "UPDATE BOTTLE_SIZE SET SIZE=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, bottle.getSize());
            preparedStatement.setInt(2, bottle.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Bottle updating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public void remove(BottleSize bottle) throws DaoException {
        throw new DaoException("It's not allowed to delete bottle!");
    }
}
