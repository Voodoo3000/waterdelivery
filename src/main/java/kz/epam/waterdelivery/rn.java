package kz.epam.waterdelivery;

import kz.epam.waterdelivery.dao.h2Service.UserService;
import kz.epam.waterdelivery.entity.User;
import kz.epam.waterdelivery.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class rn {
    public static void main(String[] args) {

        UserService userService = new UserService();
        User user;
        try {
            user = userService.getById(1);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

/*        UserService userService = new UserService();
        try {
            List<User> userList = userService.getAll();

            for (User a: userList){
                System.out.println(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

/*        UserService userService = new UserService();
        User user = new User();
        user.setUserId(2);
        user.setFirstname("Pavel");
        user.setLastName("Bobylev");
        user.setLoginEmail("logr@mail.ru");
        user.setPassword("qweasdzxc");
        user.setRole(User.Role.CLIENT);
        user.setWallet(7000);
        try {
            userService.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ConnectionPool connectionPool = ConnectionPool.getInstance();

        connectionPool.initializeConnectionPool();
        Connection connection = connectionPool.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT FIRSTNAME FROM USER");
            while (resultSet.next()) {
                String res = resultSet.getString("FIRSTNAME");
                System.out.println(res);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } */
    }
}
