package com.cinema.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.logging.Logger;


public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class.getName());
    private final LinkedList<Connection> available = new LinkedList<>();
    private final LinkedList<Connection> used = new LinkedList<>();
    private final String url;
    private final String user;
    private final String password;

    public ConnectionPool(String url, String user, String password, int initConnCnt) {
        this.url = url;
        this.user = user;
        this.password = password;
        for (int i = 0; i < initConnCnt; i++) {
            available.add(getConnection());
        }
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            log.severe("Cannot obtain a connection from pool");
            log.severe(e.getMessage());
        }
        return con;
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
                throw new IllegalStateException("Connection not in the used array");
            }
        }
    }

}
