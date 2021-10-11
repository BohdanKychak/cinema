package com.cinema.app.servlet;

import com.cinema.app.dao.MoviesDAO;
import com.cinema.app.model.Movies;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;;
import java.util.List;

@WebServlet("/movies")
public class MoviesServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // toDo add position and limit
        String pageNumber = request.getParameter("pageNumber");
        MoviesDAO moviesDAO = new MoviesDAO();
        List<Movies> list = moviesDAO.getMovies();

        request.setAttribute("list", list);

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/movies.jsp");
        dispatcher.forward(request, response);
    }

}
