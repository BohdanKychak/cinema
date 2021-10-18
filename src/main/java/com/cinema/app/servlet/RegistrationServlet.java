package com.cinema.app.servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.service.UserService;

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
        String role = "user";

        boolean correct = false;
        if (conditionsMet(login, password)) {
            UserService userService = new UserService();
            correct = userService.createUser(login, password, role);
        }


        if (correct) {
            response.sendRedirect("/cinema/congratulations.jsp");
        } else {
            String errorMessage = "Error. Such a user exists or the specified data does not meet the conditions of registration";
            request.setAttribute("errorMessage", errorMessage);
            doGet(request, response);
        }
    }

    public static boolean conditionsMet(String login, String password) {
        boolean conditionsMet = false;
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
        if (password.matches(pattern)) {
            if (!Objects.equals(login, "") && !Objects.equals(password, "")) {
                conditionsMet = true;
            }
        }
        return conditionsMet;
    }

}
