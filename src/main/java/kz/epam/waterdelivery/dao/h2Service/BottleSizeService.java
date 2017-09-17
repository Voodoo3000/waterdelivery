package kz.epam.waterdelivery.dao.h2Service;

import kz.epam.waterdelivery.dao.BottleSizeDAO;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BottleSizeService implements BottleSizeDAO {

    ConnectionPool pool =  ConnectionPool.getInstance();
    Connection connection = pool.getConnection();


    @Override
    public void add(BottleSize bottle) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO BOTTLE_SIZE (ID, SIZE) VALUES(?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bottle.getBottleId());
            preparedStatement.setDouble(2, bottle.getSize());

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
    public List<BottleSize> getAll() throws SQLException {
        List<BottleSize> bottleSizeList = new ArrayList<>();

        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                BottleSize bottle = new BottleSize();
                bottle.setBottleId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));

                bottleSizeList.add(bottle);
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
        return bottleSizeList;
    }

    @Override
    public BottleSize getById(int bottleId) throws SQLException {
        PreparedStatement preparedStatement = null;
        BottleSize bottle = new BottleSize();
        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bottleId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bottle.setBottleId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));
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
            return bottle;
        }
    }

    @Override
    public void update(BottleSize bottle) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE BOTTLE_SIZE SET SIZE=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, bottle.getSize());
            preparedStatement.setInt(2, bottle.getBottleId());


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
    public void remove(BottleSize bottle) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM BOTTLE_SIZE WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bottle.getBottleId());

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
