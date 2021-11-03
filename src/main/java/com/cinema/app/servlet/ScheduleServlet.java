package com.cinema.app.servlet;

import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.model.Movies;
import com.cinema.app.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(Constants.URL_SCHEDULE)
public class ScheduleServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toDo add position and limit
        String pageNumber = request.getParameter("pageNumber");
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        List<Movies> list = scheduleDAO.getSession();

        request.setAttribute(Constants.LIST, list);

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_SCHEDULE);
        dispatcher.forward(request, response);
    }

}
