package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.CustomerOrder;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDao implements GenericDao<CustomerOrder> {

    ConnectionPool pool = ConnectionPool.getInstance();


    @Override
    public void add(CustomerOrder order) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO CUSTOMER_ORDER (CUSTOMER_ID, AMOUNT, PAYMENT, ORDER_DATE, ADDRESS) VALUES(?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setBoolean(3, order.isPayment());
            preparedStatement.setDate(4,  order.getCurrentDate());
            preparedStatement.setString(5, order.getAddress());

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
    public List<CustomerOrder> getAll() throws SQLException {
        Connection connection = pool.getConnection();
        List<CustomerOrder> orderList = new ArrayList<>();

        String sql = "SELECT ID, CUSTOMER_ID, AMOUNT, PAYMENT, ORDER_DATE, ADDRESS FROM CUSTOMER_ORDER";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                CustomerOrder order = new CustomerOrder();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT "));
                order.setPayment(resultSet.getBoolean("PAYMENT"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
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
                pool.returnConnection(connection);
            }
        }
        return orderList;
    }

    @Override
    public CustomerOrder getById(int id) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        CustomerOrder order = null;
        String sql ="SELECT ID, CUSTOMER_ID, AMOUNT, PAYMENT, ORDER_DATE, ADDRESS FROM CUSTOMER_ORDER WHERE ID=?";;
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = new CustomerOrder();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setPayment(resultSet.getBoolean("PAYMENT"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setAddress(resultSet.getString("ADDRESS"));
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
            return order;
        }
    }

    public Integer getByUserId(int userId) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        Integer order = null;
        String sql ="SELECT MAX(ID) FROM CUSTOMER_ORDER WHERE CUSTOMER_ID =?";;
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = resultSet.getInt("MAX(ID)");
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
            return order;
        }
    }

    public CustomerOrder getUnpaidOrderByUserId(int userId) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        CustomerOrder order = null;
        String sql ="SELECT ID, CUSTOMER_ID, AMOUNT, PAYMENT, ORDER_DATE, ADDRESS FROM CUSTOMER_ORDER WHERE CUSTOMER_ID=? AND PAYMENT=0";;
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = new CustomerOrder();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                order.setAmount(resultSet.getDouble("AMOUNT"));
                order.setPayment(resultSet.getBoolean("PAYMENT"));
                order.setOrderDate(resultSet.getDate("ORDER_DATE"));
                order.setAddress(resultSet.getString("ADDRESS"));
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
            return order;
        }
    }

    @Override
    public void update(CustomerOrder order) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE CUSTOMER_ORDER SET CUSTOMER_ID=?, AMOUNT=?, PAYMENT=?, ORDER_DATE=?, ADDRESS=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setDouble(2, order.getAmount());
            preparedStatement.setBoolean(3, order.isPayment());
            preparedStatement.setDate(4, order.getCurrentDate());
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setInt(6, order.getId());

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
    public void remove(CustomerOrder order) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM CUSTOMER_ORDER WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getId());

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
