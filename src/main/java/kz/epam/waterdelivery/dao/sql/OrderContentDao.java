package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.entity.Water;
import kz.epam.waterdelivery.pool.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderContentDao implements GenericDao<OrderContent> {

    ConnectionPool pool =  ConnectionPool.getInstance();

    @Override
    public void add(OrderContent content) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO ORDER_CONTENT (WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID) VALUES(?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, content.getWater().getId());
            preparedStatement.setInt(2, content.getBottleSize().getId());
            preparedStatement.setInt(3, content.getQuantity());
            preparedStatement.setInt(4, content.getCustomerOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<OrderContent> getAll() throws SQLException {
        Connection connection = pool.getConnection();
        List<OrderContent> contentList = new ArrayList<>();
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID FROM ORDER_CONTENT";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            WaterDao waterDao = new WaterDao();
            BottleSizeDao bottleSizeDao = new BottleSizeDao();
            while (resultSet.next()) {
                OrderContent content = new OrderContent();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return contentList;
    }

    public List<OrderContent> getByCustomerOrderId(int orderId) {
        Connection connection = pool.getConnection();
        List<OrderContent> contentList = new ArrayList<>();
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID FROM ORDER_CONTENT WHERE CUSTOMER_ORDER_ID=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            WaterDao waterDao = new WaterDao();
            BottleSizeDao bottleSizeDao = new BottleSizeDao();
            while (resultSet.next()) {
                OrderContent content = new OrderContent();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return contentList;
    }

    @Override
    public OrderContent getById(int id) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        OrderContent content = new OrderContent();
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, CUSTOMER_ORDER_ID FROM ORDER_CONTENT WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            WaterDao waterDao = new WaterDao();
            BottleSizeDao bottleSizeDao = new BottleSizeDao();

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                pool.returnConnection(connection);
            }
            return content;
        }
    }

    @Override
    public void update(OrderContent content) throws SQLException {
        Connection connection = pool.getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public void remove(OrderContent content) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM ORDER_CONTENT WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, content.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }
}
