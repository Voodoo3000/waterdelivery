package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.WaterType;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaterTypeImpl implements GenericDao<WaterType> {

    ConnectionPool pool =  ConnectionPool.getInstance();

    @Override
    public void add(WaterType waterType) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO WATER_TYPE (ID, TYPE) VALUES(?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, waterType.getId());
            preparedStatement.setString(2, waterType.getType());

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
    public List<WaterType> getAll() {
        Connection connection = pool.getConnection();
        List<WaterType> waterTypes = new ArrayList<>();

        String sql = "SELECT ID, TYPE FROM WATER_TYPE";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                WaterType waterType = new WaterType();
                waterType.setId(resultSet.getInt("ID"));
                waterType.setType(resultSet.getString("TYPE"));

                waterTypes.add(waterType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return waterTypes;
    }

    @Override
    public WaterType getById(int id) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        WaterType waterType = new WaterType();
        String sql = "SELECT ID, TYPE FROM WATER_TYPE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                waterType.setId(resultSet.getInt("ID"));
                waterType.setType(resultSet.getString("TYPE"));
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
            return waterType;
        }
    }

    @Override
    public void update(WaterType waterType) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE WATER_TYPE SET TYPE=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, waterType.getType());
            preparedStatement.setInt(2, waterType.getId());


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
    public void remove(WaterType waterType) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM WATER_TYPE WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, waterType.getId());

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
