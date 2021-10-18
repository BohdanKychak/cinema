package com.cinema.app.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.service.UserService;;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {

    public CreateAccountServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createAccountView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {


        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        boolean correct = false;
        if (RegistrationServlet.conditionsMet(login, password)) {
            UserService userService = new UserService();
            correct = userService.createUser(login, password, role);
        }

        if (correct) {
            response.sendRedirect("/cinema/createAccount");
        } else {
            String errorMessage = "Error. Such a user exists or the specified data does not meet the conditions of registration";
            request.setAttribute("errorMessage", errorMessage);
            doGet(request, response);
        }

    }

}