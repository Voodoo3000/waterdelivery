package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.OrderContent;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderContentImpl implements GenericDao<OrderContent> {

    ConnectionPool pool =  ConnectionPool.getInstance();
    Connection connection = pool.getConnection();

    @Override
    public void add(OrderContent content) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO ORDER_CONTENT (ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY) VALUES(?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, content.getId());
            preparedStatement.setInt(2, content.getWaterId());
            preparedStatement.setInt(3, content.getBottleSize());
            preparedStatement.setInt(4, content.getQuantity());


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
    public List<OrderContent> getAll() throws SQLException {
        List<OrderContent> contentList = new ArrayList<>();

        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY FROM ORDER_CONTENT";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                OrderContent content = new OrderContent();
                content.setId(resultSet.getInt("ID"));
                content.setWaterId(resultSet.getInt("WATER_ID"));
                content.setBottleSize(resultSet.getInt("BOTTLE_SIZE_ID"));
                content.setQuantity(resultSet.getInt("QUANTITY"));

                contentList.add(content);
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
        return contentList;
    }

    @Override
    public OrderContent getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        OrderContent content = new OrderContent();
        String sql = "SELECT ID, WATER_ID, BOTTLE_SIZE_ID, QUANTITY FROM ORDER_CONTENT WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                content.setId(resultSet.getInt("ID"));
                content.setWaterId(resultSet.getInt("WATER_ID"));
                content.setBottleSize(resultSet.getInt("BOTTLE_SIZE_ID"));
                content.setQuantity(resultSet.getInt("QUANTITY"));
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
            return content;
        }
    }

    @Override
    public void update(OrderContent content) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE ORDER_CONTENT SET WATER_ID=?, BOTTLE_SIZE_ID=?, QUANTITY=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, content.getWaterId());
            preparedStatement.setInt(2, content.getBottleSize());
            preparedStatement.setInt(3, content.getQuantity());
            preparedStatement.setInt(4, content.getId());

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
    public void remove(OrderContent content) throws SQLException {
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
                connection.close();
            }
        }
    }
}
