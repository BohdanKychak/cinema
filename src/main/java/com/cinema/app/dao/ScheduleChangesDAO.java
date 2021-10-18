package com.cinema.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ScheduleChangesDAO {
    private static final Logger log = Logger.getLogger(ScheduleChangesDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();

    public static boolean getAddToSchedule(long movieId, String sessionDate, String sessionTime) {

        Connection connection = dbManager.getConnection();

        try {
            String sql = "INSERT INTO schedule(movieId, sessionDate, sessionTime, price, freePlaces, hallId)" +
                    "VALUES (?,?,?, 200.00, 40, 1);";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setLong(1, movieId);
            prepareStatement.setString(2, sessionDate);
            prepareStatement.setString(3, sessionTime);
            prepareStatement.execute();

        } catch (SQLException e) {
            log.severe(e.getMessage());
            return false;
        } finally {
            dbManager.commit(connection);
        }
        return true;
    }

    public static boolean getCancelSession(long id) {
        boolean sessionExists = getSessionExists(id);
        if (!sessionExists) {
            return false;
        }

        Connection connection = dbManager.getConnection();

        try {
            String sql = "DELETE FROM schedule WHERE id=?;";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setLong(1, id);
            prepareStatement.execute();

        } catch (SQLException e) {
            log.severe(e.getMessage());
            return false;
        } finally {
            dbManager.commit(connection);
        }
        return true;
    }

    private static boolean getSessionExists(long id) {
        boolean sessionExists = false;
        List<Long> list = Collections.emptyList();
        Connection connection = dbManager.getConnection();
        String sql = "SELECT id FROM schedule;";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            list = new ArrayList<>();
            while (resultSet.next()) {
                Long sessionId = resultSet.getLong("id");
                list.add(sessionId);
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }

        for (Long l : list) {
            if (id == l) {
                sessionExists = true;
                break;
            }
        }
        return sessionExists;
    }

    public static long getMovieId(String movieTitle) {
        long id = 0;
        Connection connection = dbManager.getConnection();
        String sql = "SELECT id FROM movie WHERE movieTitle= '" + movieTitle + "' ;";
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            id = resultSet.getLong("id");
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return id;
    }

}

