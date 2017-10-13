package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.OrderContent;
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

        String sql = "INSERT INTO ORDER_CONTENT (WATER_ID, BOTTLE_SIZE_ID, QUANTITY, PRICE, CUSTOMER_ORDER_ID) VALUES(?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, content.getWaterId());
            preparedStatement.setInt(2, content.getBottleSizeId());
            preparedStatement.setInt(3, content.getQuantity());
            preparedStatement.setDouble(4, content.getPrice());
            preparedStatement.setInt(5, content.getCustomerOrderId());

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

        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, PRICE, CUSTOMER_ORDER_ID FROM ORDER_CONTENT";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                OrderContent content = new OrderContent();
                content.setId(resultSet.getInt("ID"));
                content.setWaterId(resultSet.getInt("WATER_ID"));
                content.setBottleSizeId(resultSet.getInt("BOTTLE_SIZE_ID"));
                content.setQuantity(resultSet.getInt("QUANTITY"));
                content.setPrice(resultSet.getDouble("PRICE"));
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

    @Override
    public OrderContent getById(int id) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        OrderContent content = new OrderContent();
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY, PRICE, CUSTOMER_ORDER_ID FROM ORDER_CONTENT WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                content.setId(resultSet.getInt("ID"));
                content.setWaterId(resultSet.getInt("WATER_ID"));
                content.setBottleSizeId(resultSet.getInt("BOTTLE_SIZE_ID"));
                content.setQuantity(resultSet.getInt("QUANTITY"));
                content.setPrice(resultSet.getDouble("PRICE"));
                content.setCustomerOrderId(resultSet.getInt("CUSTOMER_ORDER_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
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

        String sql = "UPDATE ORDER_CONTENT SET WATER_ID=?, BOTTLE_SIZE_ID=?, QUANTITY=?, PRICE=?, CUSTOMER_ORDER_ID=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, content.getWaterId());
            preparedStatement.setInt(2, content.getBottleSizeId());
            preparedStatement.setInt(3, content.getQuantity());
            preparedStatement.setDouble(4, content.getPrice());
            preparedStatement.setInt(5, content.getCustomerOrderId());
            preparedStatement.setInt(6, content.getId());

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
