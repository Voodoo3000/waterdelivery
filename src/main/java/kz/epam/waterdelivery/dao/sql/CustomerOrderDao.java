package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.command.SignInCommand;
import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDao implements GenericDao<CustomerOrder> {

    private static final Logger LOGGER = Logger.getLogger(CustomerOrderDao.class);
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(CustomerOrder order) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO CUSTOMER_ORDER (CUSTOMER_ID, AMOUNT, ORDER_DATE, STATUS) VALUES(?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDate(3,  order.getCurrentDate());
            preparedStatement.setString(4, String.valueOf(order.getStatus()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Order creating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<CustomerOrder> getAll() throws DaoException {
        Connection connection = pool.getConnection();
        CustomerOrder order;
        List<CustomerOrder> orderList = new ArrayList<>();
        String sql = "SELECT ID, CUSTOMER_ID, AMOUNT, ORDER_DATE, STATUS FROM CUSTOMER_ORDER";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                order = new CustomerOrder();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setStatus(CustomerOrder.Status.valueOf(resultSet.getString("STATUS")));
                orderList.add(order);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all orders SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return orderList;
    }

    public List<CustomerOrder> getAllByClientId(int id) throws DaoException {
        Connection connection = pool.getConnection();
        CustomerOrder order;
        List<CustomerOrder> orderList = new ArrayList<>();
        String sql = "SELECT ID, CUSTOMER_ID, AMOUNT, ORDER_DATE, STATUS FROM CUSTOMER_ORDER WHERE CUSTOMER_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new CustomerOrder();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setStatus(CustomerOrder.Status.valueOf(resultSet.getString("STATUS")));
                orderList.add(order);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all orders by client id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return orderList;
    }

    @Override
    public CustomerOrder getById(int id) throws DaoException {
        Connection connection = pool.getConnection();
        CustomerOrder order = null;
        String sql ="SELECT ID, CUSTOMER_ID, AMOUNT, ORDER_DATE, STATUS FROM CUSTOMER_ORDER WHERE ID=?";;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new CustomerOrder();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setStatus(CustomerOrder.Status.valueOf(resultSet.getString("STATUS")));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get order by id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return order;
    }

    public Integer getByUserId(int userId) throws DaoException {
        Connection connection = pool.getConnection();
        Integer order = null;
        String sql ="SELECT MAX(ID) FROM CUSTOMER_ORDER WHERE CUSTOMER_ID =?";;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = resultSet.getInt("MAX(ID)");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get the last one order by user id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return order;
    }

    public CustomerOrder getCreatingOrderByUserId(int userId) throws DaoException {
        Connection connection = pool.getConnection();
        CustomerOrder order = null;
        String sql ="SELECT ID, CUSTOMER_ID, AMOUNT, ORDER_DATE, STATUS FROM CUSTOMER_ORDER WHERE CUSTOMER_ID=? AND STATUS='CREATING'";;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new CustomerOrder();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setStatus(CustomerOrder.Status.valueOf(resultSet.getString("STATUS")));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get creating order by user id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return order;
    }

    @Override
    public void update(CustomerOrder order) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "UPDATE CUSTOMER_ORDER SET CUSTOMER_ID=?, AMOUNT=?, ORDER_DATE=?, STATUS=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setDate(3, order.getCurrentDate());
            preparedStatement.setString(4, String.valueOf(order.getStatus()));
            preparedStatement.setInt(5, order.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Order updating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public void remove(CustomerOrder order) throws DaoException {
        throw new DaoException("It's not allowed to delete order!");
    }
}
