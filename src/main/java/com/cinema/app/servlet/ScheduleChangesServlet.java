package com.cinema.app.servlet;

import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.service.ScheduleChangesService;
import com.cinema.app.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

        int code = ScheduleChangesService.checkData(id, movieTitle, sessionTime);
        boolean correct = ScheduleChangesService.isDone(code, id, movieTitle, sessionTime);

        if (correct) {
            response.sendRedirect(Constants.JSP_DONE);
        } else {
            request.setAttribute(Constants.MESSAGE, ScheduleChangesService.getErrorMessage(code));
            doGet(request, response);
        }
    }


}
