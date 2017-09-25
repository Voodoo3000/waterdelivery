package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.WaterDao;
import kz.epam.waterdelivery.entity.Water;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaterImpl implements WaterDao {

    ConnectionPool pool =  ConnectionPool.getInstance();
    Connection connection = pool.getConnection();

    @Override
    public void add(Water water) throws SQLException {

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO WATER(ID, WATER_TYPE_ID, PRICE_PER_LITER) VALUES(?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, water.getWaterId());
            preparedStatement.setInt(2, water.getWaterTypeId());
            preparedStatement.setInt(3, water.getPricePerLiter());

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
    public List<Water> getAll() throws SQLException {
        List<Water> waterList = new ArrayList<>();

        String sql = "SELECT ID, WATER_TYPE_ID, PRICE_PER_LITER FROM WATER";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Water water = new Water();
                water.setWaterId(resultSet.getInt("ID"));
                water.setWaterTypeId(resultSet.getInt("WATER_TYPE_ID"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));

                waterList.add(water);
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
        return waterList;
    }

    @Override
    public Water getById(int waterId) throws SQLException {
        PreparedStatement preparedStatement = null;
        Water water = new Water();
        String sql = "SELECT ID, WATER_TYPE_ID, PRICE_PER_LITER FROM WATER WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, waterId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                water.setWaterId(resultSet.getInt("ID"));
                water.setWaterTypeId(resultSet.getInt("WATER_TYPE_ID"));
                water.setPricePerLiter(resultSet.getInt("PRICE_PER_LITER"));
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
            return water;
        }
    }

    @Override
    public void update(Water water) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE WATER SET WATER_TYPE_ID=?, PRICE_PER_LITER=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, water.getWaterTypeId() );
            preparedStatement.setInt(2, water.getPricePerLiter());
            preparedStatement.setInt(3, water.getWaterId());

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
    public void remove(Water water) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM WATER WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, water.getWaterId());

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
