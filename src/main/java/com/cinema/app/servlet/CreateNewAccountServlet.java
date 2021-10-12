package com.cinema.app.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.dao.RegistrationDAO;
import com.cinema.app.utils.SearchForRegistrationErrorsUtils;

@WebServlet("/createNewAccount")
public class CreateNewAccountServlet extends HttpServlet {

    public CreateNewAccountServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createNewAccountView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");


        boolean correct = createNewUser(request, response, login, password, role);

        if (correct) {
            response.sendRedirect("/cinema/createNewAccount");

        }

    }

    public boolean createNewUser(HttpServletRequest request, HttpServletResponse response,
                                 String login, String password, String role)
            throws ServletException, IOException {

        String errorMessage = SearchForRegistrationErrorsUtils.message(login, password);

        if (errorMessage != null) {

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createNewAccountView.jsp");

            dispatcher.forward(request, response);
            return false;
        }
        return RegistrationDAO.getRegistration(login, password, role);
    }

}