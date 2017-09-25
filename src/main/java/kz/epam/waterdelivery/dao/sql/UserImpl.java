package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.UserDao;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDao {

    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();

    @Override
    public void add(User user) throws SQLException {

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO USER(FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET) VALUES( ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setInt(6, user.getWallet());

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
    public List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET FROM USER";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getInt("WALLET"));

                userList.add(user);
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
        return userList;
    }

    @Override
    public User getByLogin(String loginEmail) throws SQLException {
        PreparedStatement preparedStatement = null;
        User user = new User();
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET FROM USER WHERE LOGINEMAIL=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, loginEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getInt("WALLET"));
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
            return user;
        }
    }

    @Override
    public User getByPass(String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        User user = new User();
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET FROM USER WHERE PASSWORD=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getInt("WALLET"));
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
            return user;
        }
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE USER SET FIRSTNAME=?, LASTNAME=?, LOGINEMAIL=?, PASSWORD=?, ROLE=?, WALLET=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setInt(6, user.getWallet());
            preparedStatement.setInt(7, user.getUserId());

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
    public void remove(User user) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM USER WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getUserId());

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
