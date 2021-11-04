package com.cinema.app.dao;

import com.cinema.app.model.Movies;
import com.cinema.app.utils.Constants;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ScheduleDAO {
    private static final Logger log = Logger.getLogger(ScheduleDAO.class.getName());
    private final DBManager dbManager = DBManager.getInstance();

    public List<Movies> getSession() {

        List<Movies> list = Collections.emptyList();
        Statement statement;
        ResultSet resultSet;
        Connection connection = dbManager.getConnection();
        // toDo position and limit
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.SQL_SCHEDULE);

            list = new ArrayList<>();
            while (resultSet.next()) {
                String date = resultSet.getString(Constants.SESSION_DATE);
                String today = new SimpleDateFormat(Constants.DATA_TERMS).format(new Date());
                if (date.compareTo(today) < 0) {
                    continue;
                }
                Movies movies = new Movies.Builder()
                        .withSessionId(resultSet.getLong(Constants.ID))
                        .withMovieTitle(resultSet.getString(Constants.MOVIE_TITLE))
                        .withAge(resultSet.getString(Constants.AGE))
                        .withSessionDate(resultSet.getString(Constants.SESSION_DATE))
                        .withSessionTime(resultSet.getString(Constants.SESSION_TIME))
                        .withPrice(resultSet.getDouble(Constants.PRICE))
                        .withFreePlaces(resultSet.getInt(Constants.FREE_PLACES))
                        .withHallId(resultSet.getLong(Constants.HALL_ID)).build();
                list.add(movies);
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return list;
    }

    public List<Movies> getMovie() {
        List<Movies> list = Collections.emptyList();
        Statement statement;
        ResultSet resultSet;
        Connection connection = dbManager.getConnection();
        // toDo position and limit
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.SQL_MOVIE_TITLE);

            list = new ArrayList<>();
            while (resultSet.next()) {
                Movies movies = new Movies.Builder()
                        .withMovieTitle(resultSet.getString(Constants.MOVIE_TITLE)).build();
                list.add(movies);
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return list;
    }

}
