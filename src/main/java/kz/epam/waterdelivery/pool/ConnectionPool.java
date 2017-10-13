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
