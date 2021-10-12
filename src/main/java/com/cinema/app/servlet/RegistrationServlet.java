package com.cinema.app.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.dao.AccountDAO;
import com.cinema.app.dao.RegistrationDAO;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registrationView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String errorMessage = message(login, password);

        if (errorMessage != null) {

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registrationView.jsp");

            dispatcher.forward(request, response);
            return;
        }
        boolean correct = RegistrationDAO.getRegistration(login, password);

        if(correct) {
            response.sendRedirect("/cinema/successfulRegistration");
        }

    }

    private String message(String login, String password) {
        if (Objects.equals(login, "") || Objects.equals(password, "" )) {
            return "Error! One of the fields is empty";
        }
        List<String> loginList = AccountDAO.getLogin("user");
        for (String log : loginList) {
            if (Objects.equals(login, log)) {
                return "Error! Such a user exists";
            }
        }
        return null;
    }
}
