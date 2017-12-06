package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.command.SignInCommand;
import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.Water;
import kz.epam.waterdelivery.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaterDao implements GenericDao<Water> {

    private static final Logger LOGGER = Logger.getLogger(WaterDao.class);
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(Water water) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "INSERT INTO WATER(TYPE, PRICE_PER_LITER) VALUES(?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, water.getType());
            preparedStatement.setInt(2, water.getPricePerLiter());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("User creating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<Water> getAll() throws DaoException {
        Connection connection = pool.getConnection();
        List<Water> waterList = new ArrayList<>();
        String sql = "SELECT ID, TYPE, PRICE_PER_LITER FROM WATER";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Water water = new Water();
                water.setId(resultSet.getInt("ID"));
                water.setType(resultSet.getString("TYPE"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));
                waterList.add(water);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all water SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return waterList;
    }

    @Override
    public Water getById(int id) throws DaoException {
        Connection connection = pool.getConnection();
        Water water = null;
        String sql = "SELECT ID, TYPE, PRICE_PER_LITER FROM WATER WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                water = new Water();
                water.setId(resultSet.getInt("ID"));
                water.setType(resultSet.getString("TYPE"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));
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
        return water;
    }

    public Water getByType(String type) throws DaoException {
        Connection connection = pool.getConnection();
        Water water = null;
        String sql = "SELECT ID, TYPE, PRICE_PER_LITER FROM WATER WHERE TYPE=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                water = new Water();
                water.setId(resultSet.getInt("ID"));
                water.setType(resultSet.getString("TYPE"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get user by type SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return water;
    }

    @Override
    public void update(Water water) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "UPDATE WATER SET TYPE=?, PRICE_PER_LITER=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, water.getType());
            preparedStatement.setInt(2, water.getPricePerLiter());
            preparedStatement.setInt(3, water.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Water updating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public void remove(Water water) throws DaoException {
        throw new DaoException("It's not allowed to delete water!");
    }

}
