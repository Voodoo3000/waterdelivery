package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.BottleSize;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BottleSizeDao implements GenericDao<BottleSize> {

    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(BottleSize bottle) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO BOTTLE_SIZE (SIZE) VALUES(?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, bottle.getSize());

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
    public List<BottleSize> getAll() {
        Connection connection = pool.getConnection();
        List<BottleSize> bottleSizeList = new ArrayList<>();
        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                BottleSize bottle = new BottleSize();
                bottle.setId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));

                bottleSizeList.add(bottle);
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
        return bottleSizeList;
    }

    @Override
    public BottleSize getById(int id) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        BottleSize bottle = null;
        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bottle = new BottleSize();
                bottle.setId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));
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
            return bottle;
        }
    }

    public BottleSize getBySize(double size) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;
        BottleSize bottle = null;
        String sql = "SELECT ID, SIZE FROM BOTTLE_SIZE WHERE SIZE=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, size);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bottle = new BottleSize();
                bottle.setId(resultSet.getInt("ID"));
                bottle.setSize(resultSet.getDouble("SIZE"));
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
            return bottle;

        }
    }

    @Override
    public void update(BottleSize bottle) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE BOTTLE_SIZE SET SIZE=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, bottle.getSize());
            preparedStatement.setInt(2, bottle.getId());


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
    public void remove(BottleSize bottle) throws SQLException {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM BOTTLE_SIZE WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bottle.getId());

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
