package com.cinema.app.dao;

import com.cinema.app.utils.Constants;

import java.sql.*;

import org.apache.log4j.Logger;

import static java.lang.String.format;

public class PurchaseDAO {
    private static final Logger log = Logger.getLogger(PurchaseDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();

    public static String getPurchaseCode(long id, int place, String login) {
        String purchaseCode = generationCode();

        long userId = getId(format(Constants.SQL_USER_ID, login));

        if (ticketPurchase(id, place, purchaseCode, userId)) {
            return purchaseCode;
        }
        return null;
    }

    private static boolean ticketPurchase(long id, int place, String purchaseCode, long userId) {
        if (checkAccount(userId)) {
            if (!seatReservation(userId, id, place, purchaseCode)) {
                return false;
            }
        }
        if (checkAccount(userId)) {
            withdrawMoney(userId);
            takeMoney();
        }
        return true;
    }

    private static boolean checkAccount(long userId) {
        return !(getBalance(format(Constants.SQL_GET_BALANCE, userId)) < Constants.PRICE_OF_SESSION);
    }

    private static void withdrawMoney(long userId) {
        Connection connection = dbManager.getConnection();

        try {
            double money = getBalance(format(Constants.SQL_GET_BALANCE, userId));
            money -= Constants.PRICE_OF_SESSION;

            moneyTransactions(connection, money, userId);

        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
    }

    private static void takeMoney() {
        Connection connection = dbManager.getConnection();

        try {
            double money = getBalance(format(Constants.SQL_GET_BALANCE, Constants.CINEMA_ID));
            money += Constants.PRICE_OF_SESSION;

            moneyTransactions(connection, money, Constants.CINEMA_ID);

        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
    }

    private static void moneyTransactions(Connection connection, double money, long userId) throws SQLException {
        PreparedStatement prepareStatement = connection.prepareStatement(Constants.SQL_MONEY_TRANSACTIONS);
        prepareStatement.setDouble(1, money);
        prepareStatement.setLong(2, userId);
        prepareStatement.execute();
    }

    private static boolean seatReservation(long userId, long id, int place, String purchaseCode) {
        Connection connection = dbManager.getConnection();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(Constants.SQL_SEAT_RESERVATION);
            prepareStatement.setLong(1, userId);
            prepareStatement.setLong(2, id);
            prepareStatement.setInt(3, place);
            prepareStatement.setString(4, purchaseCode);
            prepareStatement.execute();
            int freePlaces = getFreePlaces(id);
            prepareStatement = connection.prepareStatement(Constants.SQL_NEW_NUMBER_OF_SEATS);
            prepareStatement.setInt(1, --freePlaces);
            prepareStatement.setLong(2, id);
            prepareStatement.execute();

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } finally {
            dbManager.commit(connection);
        }
        return true;
    }

    private static String generationCode() {
        return Long.toString(Constants.MIN_GENERATION_CODE + (long) (Math.random() * Constants.MAX_GENERATION_CODE));
    }

    public static long getId(String sql) {
        Connection connection = dbManager.getConnection();
        long id = 0;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getLong(Constants.ID);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return id;
    }

    public static double getBalance(String sql) {
        Connection connection = dbManager.getConnection();
        double money = 0;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                money = resultSet.getDouble(Constants.MONEY);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return money;
    }

    public static int getFreePlaces(long id) {
        Connection connection = dbManager.getConnection();
        int places = 0;
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(format(Constants.SQL_GET_FREE_PLACES, id));
            if (resultSet.next()) {
                places = resultSet.getInt(Constants.FREE_PLACES);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return places;
    }

}
