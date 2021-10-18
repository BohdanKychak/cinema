package com.cinema.app.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.dao.ScheduleChangesDAO;
import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.model.Movies;

@WebServlet("/scheduleChanges")
public class ScheduleChangesServlet extends HttpServlet {

    public ScheduleChangesServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        List<Movies> list = scheduleDAO.getMovie();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/scheduleChangesView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String id = request.getParameter("id");
        String movieTitle = request.getParameter("movieTitle");
        String sessionDate = request.getParameter("sessionDate");
        String sessionTime = request.getParameter("sessionTime");

        String errorMessage = getErrorMassage(id, movieTitle, sessionDate, sessionTime);
        boolean correct = false;
        if (!Objects.equals(id, "")) {
            long sessionId = Long.parseLong(id);
            correct = ScheduleChangesDAO.getCancelSession(sessionId);
        } else if (!Objects.equals(movieTitle, "") && !Objects.equals(sessionDate, "") && !Objects.equals(sessionTime, "")) {
            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            if (sessionDate.compareTo(today) > 0) {
                long movieId = ScheduleChangesDAO.getMovieId(movieTitle);
                correct = ScheduleChangesDAO.getAddToSchedule(movieId, sessionDate, sessionTime);
            }
        }

        if (correct) {
            response.sendRedirect("/cinema/scheduleChanges");
        } else {
            request.setAttribute("errorMessage", errorMessage);
            doGet(request, response);
        }

    }

    private String getErrorMassage(String id, String movieTitle, String date, String time) {
        String errorMessage;

        if (!Objects.equals(id, "")) {
            errorMessage = "Error. The specified session ID does not exist";
        } else if (!Objects.equals(movieTitle, "") && !Objects.equals(date, "") && !Objects.equals(time, "")) {
            errorMessage = "Error. You can add a movie to the schedule no later than the day before it is shown";
        } else {
            errorMessage = "Error. One of the fields is empty";
        }

        return errorMessage;
    }

}
