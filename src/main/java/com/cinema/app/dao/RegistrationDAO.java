package com.cinema.app.dao;

import com.cinema.app.utils.Constants;

import java.sql.*;

import org.apache.log4j.Logger;


public class RegistrationDAO {
    private static final Logger log = Logger.getLogger(AccountDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();

    public static boolean getRegistration(String login, String password, String bankAccount, String role) {

        Connection connection = dbManager.getConnection();

        int stagesOfRegistration = 0;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(Constants.SQL_REGISTRATION);
            prepareStatement.setString(1, login);
            prepareStatement.setString(2, password);
            prepareStatement.setString(3, role);
            prepareStatement.execute();
            stagesOfRegistration++;
            prepareStatement = connection.prepareStatement(Constants.SQL_ADD_ACCOUNT);
            prepareStatement.setString(1, bankAccount);
            prepareStatement.setDouble(2, getRandomMoney());
            prepareStatement.setLong(3, getUserId(connection));
            prepareStatement.execute();
            stagesOfRegistration++;


        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } finally {
            dbManager.commit(connection);
            if (stagesOfRegistration == 1) {
                deleteUnfilledAccount(login, connection);
            }
        }
        return true;
    }

    private static long getUserId(Connection connection) {
        long id = 0;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.SQL_MAX_USER_ID);
            if (resultSet.next()) {
                id = resultSet.getLong(Constants.MAX_ID);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return id;
    }

    private static void deleteUnfilledAccount(String login, Connection connection) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(Constants.SQL_DELETE_USER);
            prepareStatement.setString(1, login);
            prepareStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    private static int getRandomMoney() {
        return Constants.MIN_MONEY_RANDOM + (int) (Math.random() * Constants.MAX_MONEY_RANDOM);
    }

}
