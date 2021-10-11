package com.cinema.app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class AccountDAO {
    private static final Logger log = Logger.getLogger(AccountDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();

    public static List<String> getLogin(String role) {
        List<String> login = Collections.emptyList();
        Connection connection = dbManager.getConnection();
        String sql = "select login from account where role='" + role + "'";
        login = getInfo(login, connection, sql, "login");
        return login;
    }

    public static List<String> getPassword(String role) {
        List<String> password = Collections.emptyList();
        Connection connection = dbManager.getConnection();
        String sql = "select password from account where role='" + role + "'";
        password = getInfo(password, connection, sql, "password");
        return password;
    }

    private static List<String> getInfo(List<String> list, Connection connection, String sql, String info) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            list = new ArrayList<>();
            while (resultSet.next()) {
                String data = resultSet.getString(info);
                list.add(data);
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return list;
    }
}
