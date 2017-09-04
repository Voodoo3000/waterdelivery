package kz.epam.waterdelivery.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:tcp://localhost/~/waterdelivery";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "test";
    private static final int POOL_SIZE = 7;
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(POOL_SIZE);

    public ConnectionPool() {
        initializeConnectionPool();
    }

    public void initializeConnectionPool() {
        while ((connections.size() < POOL_SIZE)) {
            try {
                Class.forName(DRIVER);
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                connections.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        connections.add(connection);
    }

    public synchronized void closeConnections(){
        for(Connection connection : connections){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
