package com.cinema.app.dao;

import com.cinema.app.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.logging.Logger;


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
        } catch (Exception e) {
            log.severe(Constants.ERROR_CONNECTION);
            log.severe(e.getMessage());
        }
        return connection;
    }

    public Connection retrieve() {
        Connection newConn;
        if (available.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = available.getLast();
            available.remove(newConn);
        }
        used.add(newConn);
        return newConn;
    }

    public void putBack(Connection c) {
        if (c != null) {
            if (used.remove(c)) {
                available.add(c);
            } else {
                throw new IllegalStateException(Constants.ERROR_PUT_BACK);
            }
        }
    }

}
