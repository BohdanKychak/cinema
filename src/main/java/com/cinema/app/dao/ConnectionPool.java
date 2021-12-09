package com.cinema.app.dao;

import com.cinema.app.utils.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;


public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class.getName());
    private final LinkedList<Connection> available;
    private final LinkedList<Connection> used;
    private final String url;
    private final String user;
    private final String password;

    public ConnectionPool(String url, String user, String password, int initConnCnt) {
        this.url = url;
        this.user = user;
        this.password = password;
        available = new LinkedList<>();
        used = new LinkedList<>();
        for (int i = 0; i < initConnCnt; i++) {
            available.add(getConnection());
        }
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            log.error(Constants.ERROR_CONNECTION);
            log.error(e.getMessage());
        }
        return connection;
    }

    public Connection retrieve() {
        Connection newConnection;
        if (available.size() == 0) {
            newConnection = getConnection();
        } else {
            newConnection = available.getLast();
            available.remove(newConnection);
        }
        used.add(newConnection);
        return newConnection;
    }

    public void putBack(Connection connection) {
        if (connection != null) {
            if (used.remove(connection)) {
                available.add(connection);
            } else {
                throw new IllegalStateException(Constants.ERROR_PUT_BACK);
            }
        }
    }

}
