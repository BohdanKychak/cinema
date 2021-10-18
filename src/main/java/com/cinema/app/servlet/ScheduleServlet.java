package com.cinema.app.servlet;

import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.model.Movies;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;;
import java.util.List;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toDo add position and limit
        String pageNumber = request.getParameter("pageNumber");
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        List<Movies> list = scheduleDAO.getSession();

        request.setAttribute("list", list);

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/scheduleView.jsp");
        dispatcher.forward(request, response);
    }

}
