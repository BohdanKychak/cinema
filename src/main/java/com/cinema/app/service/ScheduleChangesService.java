package com.cinema.app.service;

import com.cinema.app.dao.ScheduleChangesDAO;
import com.cinema.app.utils.Constants;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ScheduleChangesService {

    public static int checkData(String id, String movieTitle, String sessionTime) {
        if (id.matches(Constants.NUMERIC_TERMS)) {
            return 1;
        } else if (!movieTitle.trim().isEmpty() && !sessionTime.trim().isEmpty()) {
            return 2;
        }
        return 3;
    }

    public static boolean isDone(int code, String id, String movieTitle, String sessionTime) {
        switch (code) {
            case 1:
                return ScheduleChangesDAO.getCancelSession(Long.parseLong(id));
            case 2:
                Timestamp time = getTimestamp(sessionTime);
                if (time != null) {
                    long movieId = ScheduleChangesDAO.getMovieId(movieTitle);
                    return ScheduleChangesDAO.getAddToSchedule(movieId, time);
                }
            default:
                return false;
        }
    }


    static Timestamp getTimestamp(String sessionTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(sessionTime);

        if (isTimeCorrectly(localDateTime)) {
            return Timestamp.valueOf(localDateTime);
        }
        return null;
    }

    static boolean isTimeCorrectly(LocalDateTime localDateTime) {
        return LocalDateTime.now().isBefore(localDateTime) && LocalDateTime.now().getDayOfYear() != localDateTime.getDayOfYear()
                && localDateTime.getHour() >= Constants.HOUR_START && localDateTime.getHour() < Constants.HOUR_FINISH;
    }

    public static String getErrorMessage(int errorCode) {

        return switch (errorCode) {
            case 1 -> Constants.ID;
            case 2 -> Constants.MOVIE;
            default -> Constants.MESSAGE_EMPTY;
        };
    }
}
