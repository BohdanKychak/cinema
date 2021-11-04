package com.cinema.app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.cinema.app.utils.Constants;

import static java.lang.String.format;

public class AccountDAO {
    private static final Logger log = Logger.getLogger(AccountDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();


    public static List<String> getAllLogin() {
        List<String> allLogin = new ArrayList<>(getLogin(Constants.USER));
        allLogin.addAll(getLogin(Constants.ADMIN));
        return allLogin;
    }

    public static List<String> getLogin(String role) {
        String login = format(Constants.SQL_LOGIN, role);
        return getInfo(login, Constants.LOGIN);
    }

    public static List<String> getPassword(String role) {
        String password = format(Constants.SQL_PASSWORD, role);
        return getInfo(password, Constants.PASSWORD);
    }

    public static List<String> getInfo(String sql, String info) {
        List<String> list = new ArrayList<>();
        Connection connection = dbManager.getConnection();
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
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
