package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.command.SignInCommand;
import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.entity.Water;
import kz.epam.waterdelivery.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderContentDao implements GenericDao<OrderContent> {

    private static final Logger LOGGER = Logger.getLogger(OrderContentDao.class);
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(OrderContent content) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "INSERT INTO ORDER_CONTENT (WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, content.getWater().getId());
            preparedStatement.setInt(2, content.getBottleSize().getId());
            preparedStatement.setInt(3, content.getQuantity());
            preparedStatement.setInt(4, content.getCustomerOrderId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Content creating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<OrderContent> getAll() throws DaoException {
        Connection connection = pool.getConnection();
        OrderContent content;
        List<OrderContent> contentList = new ArrayList<>();
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID FROM ORDER_CONTENT";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            WaterDao waterDao = new WaterDao();
            BottleSizeDao bottleSizeDao = new BottleSizeDao();

            while (resultSet.next()) {
                content = new OrderContent();
                content.setId(resultSet.getInt("ID"));
                int water_id = resultSet.getInt("WATER_ID");
                Water water = waterDao.getById(water_id);
                content.setWater(water);
                int bottle_size_id = resultSet.getInt("BOTTLE_SIZE_ID");
                BottleSize bottleSize = bottleSizeDao.getById(bottle_size_id);
                content.setBottleSize(bottleSize);
                content.setQuantity(resultSet.getInt("QUANTITY"));
                content.setCustomerOrderId(resultSet.getInt("CUSTOMER_ORDER_ID"));
                contentList.add(content);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all content SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return contentList;
    }

    public List<OrderContent> getAllByCustomerOrderId(int orderId) throws DaoException {
        Connection connection = pool.getConnection();
        OrderContent content;
        List<OrderContent> contentList = new ArrayList<>();
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID FROM ORDER_CONTENT WHERE CUSTOMER_ORDER_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            WaterDao waterDao = new WaterDao();
            BottleSizeDao bottleSizeDao = new BottleSizeDao();
            while (resultSet.next()) {
                content = new OrderContent();
                content.setId(resultSet.getInt("ID"));
                int water_id = resultSet.getInt("WATER_ID");
                Water water = waterDao.getById(water_id);
                content.setWater(water);
                int bottle_size_id = resultSet.getInt("BOTTLE_SIZE_ID");
                BottleSize bottleSize = bottleSizeDao.getById(bottle_size_id);
                content.setBottleSize(bottleSize);
                content.setQuantity(resultSet.getInt("QUANTITY"));
                content.setCustomerOrderId(resultSet.getInt("CUSTOMER_ORDER_ID"));
                contentList.add(content);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all content by order id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return contentList;
    }

    @Override
    public OrderContent getById(int id) throws DaoException {
        Connection connection = pool.getConnection();
        OrderContent content = null;
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID FROM ORDER_CONTENT WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            WaterDao waterDao = new WaterDao();
            BottleSizeDao bottleSizeDao = new BottleSizeDao();

            if (resultSet.next()) {
                content = new OrderContent();
                content.setId(resultSet.getInt("ID"));
                int water_id = resultSet.getInt("WATER_ID");
                Water water = waterDao.getById(water_id);
                content.setWater(water);
                int bottle_size_id = resultSet.getInt("BOTTLE_SIZE_ID");
                BottleSize bottleSize = bottleSizeDao.getById(bottle_size_id);
                content.setBottleSize(bottleSize);
                content.setQuantity(resultSet.getInt("QUANTITY"));
                content.setCustomerOrderId(resultSet.getInt("CUSTOMER_ORDER_ID"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get content by id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return content;
    }

    @Override
    public void update(OrderContent content) throws DaoException {
    /*    Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE ORDER_CONTENT SET WATER_ID=?, BOTTLE_SIZE_ID=?, QUANTITY=?, CUSTOMER_ORDER_ID=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, content.getWater().getId());
            preparedStatement.setInt(2, content.getBottleSize().getId());
            preparedStatement.setInt(3, content.getQuantity());
            preparedStatement.setInt(4, content.getCustomerOrderId());
            preparedStatement.setInt(5, content.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Content updating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }*/
    }

    @Override
    public void remove(OrderContent content) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "DELETE FROM ORDER_CONTENT WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, content.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Content removing SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

}
