package com.cinema.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Logger;


public class RegistrationDAO {
    private static final Logger log = Logger.getLogger(AccountDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();

    public static boolean getRegistration(String login, String password){

        Connection connection = dbManager.getConnection();
        String sql = "INSERT INTO account (login, password, role) " +
                            "VALUES (?,?, 'user');";

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);
            prepareStatement.execute();

        } catch (SQLException e) {
            log.severe(e.getMessage());
            return false;
        } finally {
            dbManager.commit(connection);
        }
        return true;
    }

}
