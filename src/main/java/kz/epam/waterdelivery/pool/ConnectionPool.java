package kz.epam.waterdelivery.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final ResourceBundle RB = ResourceBundle.getBundle("database");
    private static final String DRIVER = RB.getString("db.driver");
    private static final String URL = RB.getString("db.url");
    private static final String LOGIN = RB.getString("db.user");
    private static final String PASSWORD = RB.getString("db.password");
    private static final int POOL_SIZE = Integer.parseInt(RB.getString("db.pool_size"));
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(POOL_SIZE);

    public ConnectionPool() {
        initializeConnectionPool();
    }

    public void initializeConnectionPool() throws ConnectionPoolException {
        while ((connections.size() < POOL_SIZE)) {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connections.add(connection);
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        return INSTANCE;
    }

    public Connection getConnection() throws ConnectionPoolException {
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
