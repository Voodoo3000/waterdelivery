package kz.epam.waterdelivery.pool;

import kz.epam.waterdelivery.entity.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(Entity.POOL_SIZE);

    public ConnectionPool() {
        initializeConnectionPool();
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        return Entity.INSTANCE;
    }

    public void initializeConnectionPool() throws ConnectionPoolException {
        while ((connections.size() < Entity.POOL_SIZE)) {
            try {
                Class.forName(Entity.DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(Entity.URL, Entity.LOGIN, Entity.PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connections.add(connection);
        }
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

    public synchronized void closeConnections() {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
