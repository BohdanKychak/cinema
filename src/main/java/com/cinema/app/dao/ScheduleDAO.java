package com.cinema.app.dao;

import com.cinema.app.model.Movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
        String sql;
        try {
             sql =" SELECT s.id, m.movietitle, m.age, m.poster, s.sessionDate, s.sessionTime, s.price, s.freePlaces, s.hallId " +
                     "FROM schedule s LEFT JOIN movie m ON m.id=s.movieId;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            list = new ArrayList<>();
            while (resultSet.next()) {
                Movies movies = new Movies.Builder()
                        .withSessionId(resultSet.getLong("id"))
                        .withMovieTitle(resultSet.getString("movieTitle"))
                        .withAge(resultSet.getString("age"))
                        .withPoster(resultSet.getString("poster"))
                        .withSessionDate(resultSet.getString("sessionDate"))
                        .withSessionTime(resultSet.getString("sessionTime"))
                        .withPrice(resultSet.getDouble("price"))
                        .withFreePlaces(resultSet.getInt("freePlaces"))
                        .withHallId(resultSet.getLong("hallId")).build();
                list.add(movies);
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return list;
    }

    public List<Movies> getMovie(){
        List<Movies> list = Collections.emptyList();
        Statement statement;
        ResultSet resultSet;
        Connection connection = dbManager.getConnection();
        // toDo position and limit
        String sql;
        try {
            sql ="SELECT movieTitle FROM movie;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            list = new ArrayList<>();
            while (resultSet.next()) {
                Movies movies = new Movies.Builder()
                        .withMovieTitle(resultSet.getString("movieTitle")).build();
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
