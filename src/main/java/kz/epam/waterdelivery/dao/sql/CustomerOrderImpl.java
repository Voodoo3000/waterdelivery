package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.CustomerOrderDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderImpl implements CustomerOrderDao {

    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();

    @Override
    public void add(CustomerOrder order) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO CUSTOMER_ORDER (ID, CUSTOMER_ID, ORDER_CONTENT_ID, AMOUNT, ADDRESS) VALUES(?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setInt(3, order.getOrderContentId());
            preparedStatement.setInt(4, order.getAmount());
            preparedStatement.setString(5, order.getAddress());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<CustomerOrder> getAll() throws SQLException {
        List<CustomerOrder> orderList = new ArrayList<>();

        String sql = "SELECT ID, CUSTOMER_ID, ORDER_CONTENT_ID, AMOUNT, ADDRESS FROM CUSTOMER_ORDER";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                CustomerOrder order = new CustomerOrder();
                order.setOrderId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setOrderContentId(resultSet.getInt("ORDER_CONTENT_ID"));
                order.setAmount(resultSet.getInt("AMOUNT"));
                order.setAddress(resultSet.getString("ADDRESS"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return orderList;
    }

    @Override
    public CustomerOrder getById(int customerId) throws SQLException {
        PreparedStatement preparedStatement = null;
        CustomerOrder order = new CustomerOrder();
        String sql = "SELECT ID, CUSTOMER_ID, ORDER_CONTENT_ID, AMOUNT, ADDRESS FROM CUSTOMER_ORDER WHERE CUSTOMER_ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order.setOrderId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setOrderContentId(resultSet.getInt("ORDER_CONTENT_ID"));
                order.setAmount(resultSet.getInt("AMOUNT"));
                order.setAddress(resultSet.getString("ADDRESS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
            return order;
        }
    }

    @Override
    public void update(CustomerOrder order) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE CUSTOMER_ORDER SET CUSTOMER_ID=?, ORDER_CONTENT_ID=?, AMOUNT=?, ADDRESS=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setInt(2, order.getOrderContentId());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setInt(5, order.getOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(CustomerOrder order) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM CUSTOMER_ORDER WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getOrderId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
