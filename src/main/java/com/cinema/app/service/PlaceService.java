package com.cinema.app.service;

import com.cinema.app.dao.AccountDAO;
import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.utils.Constants;

import java.util.List;

import static java.lang.String.format;

public class PlaceService {

    public static boolean existsId(long sessionId) {
        List<Long> allIdList = ScheduleDAO.getId(format(Constants.SQL_SESSION_ID, ScheduleDAO.getToday()));
        for (long id : allIdList) {
            if (sessionId == id) {
                return true;
            }
        }
        return false;
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
                if (str.equals(Integer.toString(place))) {
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
