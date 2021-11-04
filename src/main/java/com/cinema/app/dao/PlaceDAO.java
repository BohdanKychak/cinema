package com.cinema.app.dao;

import com.cinema.app.utils.Constants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

public class PlaceDAO {
    private static final Logger log = Logger.getLogger(PlaceDAO.class.getName());
    private static final DBManager dbManager = DBManager.getInstance();


    public static boolean existsId(long sessionId) {
        List<Long> allIdList = getSessionId();
        for (long id : allIdList) {
            if (sessionId == id) {
                return true;
            }
        }
        return false;
    }

    private static List<Long> getSessionId() {
        List<Long> list = Collections.emptyList();
        Statement statement;
        ResultSet resultSet;
        Connection connection = dbManager.getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.SQL_SESSION_ID);
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

    public static String getMessageAboutPlace(String sessionId) {
        List<String> occupiedSeatsList = getListPlace(sessionId);

        if (occupiedSeatsList.isEmpty()) {
            return Constants.MESSAGE_SEATS_FREE;
        }

        StringBuilder freeSeats = new StringBuilder(Constants.MESSAGE_FREE_PLACE);
        int occupiedSeats = 0;

        for (int place = Constants.MIN_SEATS_HALL; place <= Constants.MAX_SEATS_HALL; place++) {
            boolean free = true;
            for (String str : occupiedSeatsList) {
                if (str.equals("" + place)) {
                    free = false;
                    occupiedSeats++;
                    break;
                }
            }
            if (free) {
                freeSeats.append(place).append(", ");
            }
        }

        if (occupiedSeats < Constants.MAX_SEATS_HALL) {
            return freeSeats.substring(0, freeSeats.length() - 2);
        }

        return Constants.MESSAGE_SEATS_TAKEN;
    }

    private static List<String> getListPlace(String sessionId) {
        String place = format(Constants.SQL_PLACE, sessionId);
        return AccountDAO.getInfo(place, Constants.PLACE);
    }
}