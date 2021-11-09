package com.cinema.app.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.dao.ScheduleChangesDAO;
import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.utils.Constants;

@WebServlet(Constants.URL_SCHEDULE_CHANGES)
public class ScheduleChangesServlet extends HttpServlet {

    public ScheduleChangesServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        request.setAttribute(Constants.LIST, scheduleDAO.getMovie());
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_SCHEDULE_CHANGES);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = request.getParameter(Constants.ID);
        String movieTitle = request.getParameter(Constants.MOVIE_TITLE);
        String sessionTime = request.getParameter(Constants.SESSION_TIME);

        boolean correct = false;
        int errorCode = 0;
        if (id.matches(Constants.NUMERIC_TERMS)) {
            errorCode = 1;
            long sessionId = Long.parseLong(id);
            correct = ScheduleChangesDAO.getCancelSession(sessionId);
        } else if (!movieTitle.trim().isEmpty() && !sessionTime.trim().isEmpty()) {
            errorCode = 2;
            sessionTime = getSessionTime(sessionTime);
            if (sessionTime != null) {
                String today = new SimpleDateFormat(Constants.TIMESTAMP_TERMS).format(new Date());
                if (sessionTime.compareTo(today) > 0) {
                    long movieId = ScheduleChangesDAO.getMovieId(movieTitle);
                    correct = ScheduleChangesDAO.getAddToSchedule(movieId, sessionTime);
                }
            }
        }
        String message = getErrorMassage(errorCode);

        if (!correct) {
            request.setAttribute(Constants.MESSAGE, message);
        }
        doGet(request, response);

    }

    private String getSessionTime(String sessionTime) {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(sessionTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.TIMESTAMP_TERMS);
            return localDateTime.format(formatter); // "1986-04-08 12:30"
        } catch (Exception e) {
            return null;
        }

    }

    private String getErrorMassage(int errorCode) {

        return switch (errorCode) {
            case 1 -> Constants.ERROR_ID;
            case 2 -> Constants.ERROR_MOVIE_SESSION;
            default -> Constants.ERROR_EMPTY;
        };
    }

}
