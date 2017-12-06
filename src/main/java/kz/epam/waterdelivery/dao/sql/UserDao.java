package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.command.SignInCommand;
import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.pool.ConnectionPool;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements GenericDao<User> {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(User user) throws DaoException {

        Connection connection = pool.getConnection();

        String sql = "INSERT INTO USER(FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE) VALUES( ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setDouble(6, user.getWallet());
            preparedStatement.setString(7, String.valueOf(user.getState()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("User creating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        Connection connection = pool.getConnection();

        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER";

        try {
            Statement statement = connection.createStatement();
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
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all users SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return userList;
    }

    public List<User> getAllUsersByLogin(String loginEmail) throws DaoException {

        Connection connection = pool.getConnection();

        User user;
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER WHERE LOGINEMAIL=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all users by login SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return userList;
    }

    @Override
    public User getById(int id) throws DaoException {

        Connection connection = pool.getConnection();

        User user = null;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

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
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get user by id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return user;
    }

    public User getByLogin(String loginEmail) throws DaoException {

        Connection connection = pool.getConnection();

        User user = null;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, LOGINEMAIL, PASSWORD, ROLE, WALLET, STATE FROM USER WHERE LOGINEMAIL=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

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
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get user by login SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "UPDATE USER SET FIRSTNAME=?, LASTNAME=?, LOGINEMAIL=?, PASSWORD=?, ROLE=?, WALLET=?, STATE=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.setDouble(6, user.getWallet());
            preparedStatement.setString(7, String.valueOf(user.getState()));
            preparedStatement.setInt(8, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("User updating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public void remove(User user) throws DaoException {
        throw new DaoException("It's not allowed to delete user!");
    }
}
