package com.cinema.app.dao;

import com.cinema.app.model.Movies;
import com.cinema.app.model.Page;
import com.cinema.app.model.SchedulePage;
import com.cinema.app.model.SortBy;
import com.cinema.app.utils.Constants;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

public class ScheduleDAO {
    private static final Logger log = Logger.getLogger(ScheduleDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();

    public SchedulePage getSchedule(int position, int pageSize, String sort, String sortOrder, String filterAge) {
        SchedulePage schedulePage = null;


        Connection connection = dbManager.getConnection();
        try {
            List<Movies> list = getMovies(position, pageSize, sort, sortOrder, filterAge, connection);

            int total = getTotal(connection, filterAge);

            Page page = new Page.Builder()
                    .withTotal(total)
                    .withPosition(position)
                    .withPageSize(pageSize)
                    .withNumberOfPages(total, pageSize).build();
            SortBy sortBy = new SortBy.Builder()
                    .withSort(sort)
                    .withSortOrder(sortOrder)
                    .withFilterAge(filterAge).build();
            schedulePage = new SchedulePage.Builder()
                    .withPage(page)
                    .withSortBy(sortBy)
                    .withMovies(list).build();

        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }

        return schedulePage;
    }

    private static String getFilter(String filterAge, String sql) {
        return filterAge.isEmpty() ? Constants.EMPTY : format(sql, filterAge);
    }

    private List<Movies> getMovies(int position, int pageSize, String sort, String sortOrder, String filterAge, Connection connection) throws SQLException {
        List<Movies> list = new ArrayList<>();

        String filter = getFilter(filterAge, Constants.SQL_ADDITIONAL_FILTER);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(format((Constants.SQL_SCHEDULE), getToday(), filter, sort, sortOrder, pageSize, position));
        while (resultSet.next()) {

            Movies movies = new Movies.Builder()
                    .withSessionId(resultSet.getLong(Constants.ID))
                    .withMovieTitle(resultSet.getString(Constants.MOVIE_TITLE))
                    .withAge(resultSet.getString(Constants.AGE))
                    .withSessionTime(new SimpleDateFormat(Constants.DATA_TIME_TERMS).format(resultSet.getTimestamp(Constants.SESSION_TIME)))
                    .withPrice(resultSet.getDouble(Constants.PRICE))
                    .withFreePlaces(resultSet.getInt(Constants.FREE_PLACES))
                    .withHallId(resultSet.getLong(Constants.HALL_ID)).build();
            list.add(movies);
        }
        return list;
    }

    public List<Movies> getMovie() {
        List<Movies> list = Collections.emptyList();
        Statement statement;
        ResultSet resultSet;
        Connection connection = dbManager.getConnection();
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

    public static int getTotal(Connection connection, String filterAge) {
        int total = 0;
        String filter = getFilter(filterAge, Constants.SQL_FILTER_BY_AGE);
        String filterTotal = filter.isEmpty() ? Constants.EMPTY : getFilterTotal(filter);
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(format(Constants.SQL_TOTAL, getToday(), filterTotal));
            resultSet.next();
            total = resultSet.getInt(Constants.TOTAL);
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return total;
    }

    private static String getToday() {
        return new SimpleDateFormat(Constants.DATA_TIME_TERMS).format(new Date());
    }

    private static String getFilterTotal(String filter) {
        StringBuilder filterTotal = new StringBuilder(Constants.BEGINNING_FILTER_TOTAL);
        List<Long> ageIdList = getId(format(Constants.SQL_MOVIE_ID_AGE, filter));
        for (long id : ageIdList) {
            filterTotal.append(format(Constants.MIDDLE_FILTER_TOTAL, id));
        }
        filterTotal.setLength(filterTotal.length() - 2);
        return filterTotal.append(Constants.END_FILTER_TOTAL).toString();
    }

    public static List<Long> getId(String sql) {
        List<Long> list = Collections.emptyList();
        Statement statement;
        ResultSet resultSet;
        Connection connection = dbManager.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getLong(Constants.ID));
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        } finally {
            dbManager.commit(connection);
        }
        return list;
    }

}
