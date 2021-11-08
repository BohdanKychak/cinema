package com.cinema.app.dao;

import com.cinema.app.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

public class ScheduleChangesDAO {
    private static final Logger log = Logger.getLogger(ScheduleChangesDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();

    public static boolean getAddToSchedule(long movieId, String sessionTime) {

        Connection connection = dbManager.getConnection();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(Constants.SQL_ADD_TO_SCHEDULE);
            prepareStatement.setLong(1, movieId);
            prepareStatement.setTimestamp(2, Timestamp.valueOf(sessionTime));
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
            PreparedStatement prepareStatement = connection.prepareStatement(Constants.SQL_CANCEL_SESSION);
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
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.SQL_SESSION_ID);

            list = new ArrayList<>();
            while (resultSet.next()) {
                Long sessionId = resultSet.getLong(Constants.ID);
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
        String sql = format(Constants.SQL_MOVIE_ID, movieTitle);
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            id = resultSet.getLong(Constants.ID);
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return id;
    }

}

