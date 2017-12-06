package kz.epam.waterdelivery.dao.sql;

import kz.epam.waterdelivery.dao.DaoException;
import kz.epam.waterdelivery.dao.GenericDao;
import kz.epam.waterdelivery.entity.CustomerAddress;
import kz.epam.waterdelivery.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAddressDao implements GenericDao<CustomerAddress> {

    private static final Logger LOGGER = Logger.getLogger(CustomerAddressDao.class);
    private ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void add(CustomerAddress address) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "INSERT INTO CUSTOMER_ADDRESS(CITY, STREET, HOUSE_NUMBER, APARTMENT_NUMBER, PHONE_NUMBER, CUSTOMER_ID) VALUES( ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getHouseNumber());
            preparedStatement.setString(4, address.getApartmentNumber());
            preparedStatement.setString(5, address.getPhoneNumber());
            preparedStatement.setInt(6, address.getCustomerId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Address creating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<CustomerAddress> getAll() throws DaoException {
        Connection connection = pool.getConnection();
        List<CustomerAddress> addressList = new ArrayList<>();
        String sql = "SELECT ID, CITY, STREET, HOUSE_NUMBER, APARTMENT_NUMBER, PHONE_NUMBER, CUSTOMER_ID FROM CUSTOMER_ADDRESS";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                CustomerAddress address = new CustomerAddress();
                address.setId(resultSet.getInt("ID"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setHouseNumber(resultSet.getString("HOUSE_NUMBER"));
                address.setApartmentNumber(resultSet.getString("APARTMENT_NUMBER"));
                address.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                address.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                addressList.add(address);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error("Get all addresses SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return addressList;
    }

    @Override
    public CustomerAddress getById(int id) throws DaoException {
        Connection connection = pool.getConnection();
        CustomerAddress address = null;
        String sql = "SELECT ID, CITY, STREET, HOUSE_NUMBER, APARTMENT_NUMBER, PHONE_NUMBER, CUSTOMER_ID FROM CUSTOMER_ADDRESS WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                address = new CustomerAddress();
                address.setId(resultSet.getInt("ID"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setHouseNumber(resultSet.getString("HOUSE_NUMBER"));
                address.setApartmentNumber(resultSet.getString("APARTMENT_NUMBER"));
                address.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                address.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get address by id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return address;
    }

    public CustomerAddress getByCustomerId(int id) throws DaoException {
        Connection connection = pool.getConnection();
        CustomerAddress address = null;
        String sql = "SELECT ID, CITY, STREET, HOUSE_NUMBER, APARTMENT_NUMBER, PHONE_NUMBER, CUSTOMER_ID FROM CUSTOMER_ADDRESS WHERE CUSTOMER_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                address = new CustomerAddress();
                address.setId(resultSet.getInt("ID"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setHouseNumber(resultSet.getString("HOUSE_NUMBER"));
                address.setApartmentNumber(resultSet.getString("APARTMENT_NUMBER"));
                address.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                address.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Get address by customer id SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
        return address;
    }


    @Override
    public void update(CustomerAddress address) throws DaoException {
        Connection connection = pool.getConnection();
        String sql = "UPDATE CUSTOMER_ADDRESS SET CITY=?, STREET=?, HOUSE_NUMBER=?, APARTMENT_NUMBER=?, PHONE_NUMBER=?, CUSTOMER_ID=? WHERE ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getHouseNumber());
            preparedStatement.setString(4, address.getApartmentNumber());
            preparedStatement.setString(5, address.getPhoneNumber());
            preparedStatement.setInt(6, address.getCustomerId());
            preparedStatement.setInt(7, address.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Address updating SQLException", e);
            throw new DaoException();
        } finally {
            if (connection != null) {
                pool.returnConnection(connection);
            }
        }
    }

    @Override
    public void remove(CustomerAddress entity) throws DaoException {
        throw new DaoException("It's not allowed to delete address!");
    }
}
