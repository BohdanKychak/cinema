package com.cinema.app.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.dao.ScheduleChangesDAO;
import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.model.Movies;
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
        List<Movies> list = scheduleDAO.getMovie();
        request.setAttribute(Constants.LIST, list);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_SCHEDULE_CHANGES);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = request.getParameter(Constants.ID);
        String movieTitle = request.getParameter(Constants.MOVIE_TITLE);
        String sessionDate = request.getParameter(Constants.SESSION_DATE);
        String sessionTime = request.getParameter(Constants.SESSION_TIME);

        boolean correct = false;
        int errorCode = 0;
        if (id.matches(Constants.ID_TERMS)) {
            errorCode = 1;
            long sessionId = Long.parseLong(id);
            correct = ScheduleChangesDAO.getCancelSession(sessionId);
        } else if (!movieTitle.trim().isEmpty() && !sessionDate.trim().isEmpty() && !sessionTime.trim().isEmpty()) {
            errorCode = 2;
            String today = new SimpleDateFormat(Constants.DATA_TERMS).format(new Date());
            if (sessionDate.compareTo(today) > 0) {
                long movieId = ScheduleChangesDAO.getMovieId(movieTitle);
                correct = ScheduleChangesDAO.getAddToSchedule(movieId, sessionDate, sessionTime);
            }
        }
        String errorMessage = getErrorMassage(errorCode);

        if (!correct) {
            request.setAttribute(Constants.ERROR_MASSAGE, errorMessage);
        }
        doGet(request, response);

    }

    private String getErrorMassage(int errorCode) {

        return switch (errorCode) {
            case 1 -> Constants.ERROR_ID;
            case 2 -> Constants.ERROR_MOVIE_SESSION;
            default -> Constants.ERROR_EMPTY;
        };
    }

}
