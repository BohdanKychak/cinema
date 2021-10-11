package com.cinema.app.dao;

import com.cinema.app.model.Movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class MoviesDAO {
    private static final Logger log = Logger.getLogger(MoviesDAO.class.getName());
    private final DBManager dbManager = DBManager.getInstance();

    public List<Movies> getMovies() {

        List<Movies> list = Collections.emptyList();
        Statement statement;
        ResultSet resultSet;
        Connection connection = dbManager.getConnection();
        // toDo position and limit
        String sql;
        try {
            sql = "select * from movies where hallId='1' ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            list = new ArrayList<>();
            while (resultSet.next()) {
                Movies movies = new Movies.Builder()
                        .withId(resultSet.getLong("id"))
                        .withMovieTitle(resultSet.getString("movieTitle"))
                        .withAccess(resultSet.getString("access"))
                        .withMovieTime(resultSet.getString("movieTime"))
                        .withMovieDate(resultSet.getString("movieDate"))
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

}
