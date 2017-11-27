package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.pool.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements GenericDao<User> {

    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(User user) throws SQLException {

        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO USER(FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE) VALUES( ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setDouble(6, user.getWallet());
            preparedStatement.setString(7, String.valueOf(user.getState()));
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
    public List<User> getAll() {
        Connection connection = pool.getConnection();

        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER";
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getDouble("WALLET"));
                user.setState(User.State.valueOf(resultSet.getString("STATE")));
                userList.add(user);
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
        return userList;
    }

    public List<User> getAllUsersByLogin(String loginEmail) {

        Connection connection = pool.getConnection();

        User user;
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER WHERE LOGINEMAIL=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getDouble("WALLET"));
                user.setState(User.State.valueOf(resultSet.getString("STATE")));
                userList.add(user);
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
        }
        return userList;
    }

    @Override
    public User getById(int id) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        User user = null;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getDouble("WALLET"));
                user.setState(User.State.valueOf(resultSet.getString("STATE")));
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
            return user;
        }
    }

    public User getByLogin(String loginEmail) {

        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        User user = null;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER WHERE LOGINEMAIL=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, loginEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getDouble("WALLET"));
                user.setState(User.State.valueOf(resultSet.getString("STATE")));
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
            return user;
        }
    }


    public User getByPass(String password) throws SQLException {

        Connection connection = pool.getConnection();

        PreparedStatement preparedStatement = null;
        User user = new User();
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER WHERE PASSWORD=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("ID"));
                user.setFirstName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setLoginEmail(resultSet.getString("LOGINEMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(User.Role.valueOf(resultSet.getString("ROLE")));
                user.setWallet(resultSet.getDouble("WALLET"));
                user.setState(User.State.valueOf(resultSet.getString("STATE")));
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
    public void update(User user) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE USER SET FIRSTNAME=?, LASTNAME=?, LOGINEMAIL=?, PASSWORD=?, ROLE=?, WALLET=?, STATE=? WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setDouble(6, user.getWallet());
            preparedStatement.setString(7, String.valueOf(user.getState()));
            preparedStatement.setInt(8, user.getId());
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
    public void remove(User user) throws SQLException {

        Connection connection = pool.getConnection();

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM USER WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getId());

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
