package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.Water;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaterDao implements GenericDao<Water> {

    ConnectionPool pool =  ConnectionPool.getInstance();

    @Override
    public void add(Water water) {

        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO WATER(TYPE, PRICE_PER_LITER) VALUES(?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, water.getType());
            preparedStatement.setInt(2, water.getPricePerLiter());

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
    public List<Water> getAll() {
        Connection connection = pool.getConnection();
        List<Water> waterList = new ArrayList<>();

        String sql = "SELECT ID, TYPE, PRICE_PER_LITER FROM WATER";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Water water = new Water();
                water.setId(resultSet.getInt("ID"));
                water.setType(resultSet.getString("TYPE"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));

                waterList.add(water);
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
        return waterList;
    }

    @Override
    public Water getById(int id) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        Water water = null;
        String sql = "SELECT ID, TYPE, PRICE_PER_LITER FROM WATER WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                water = new Water();
                water.setId(resultSet.getInt("ID"));
                water.setType(resultSet.getString("TYPE"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));
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
            return water;
        }
    }

    public Water getByType(String type){
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        Water water = null;
        String sql = "SELECT ID, TYPE, PRICE_PER_LITER FROM WATER WHERE TYPE=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                water = new Water();
                water.setId(resultSet.getInt("ID"));
                water.setType(resultSet.getString("TYPE"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));
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
            return water;
        }
    }

    @Override
    public void update(Water water) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE WATER SET TYPE=?, PRICE_PER_LITER=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, water.getType());
            preparedStatement.setInt(2, water.getPricePerLiter());
            preparedStatement.setInt(3, water.getId());

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
    public void remove(Water water) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM WATER WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, water.getId());

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
