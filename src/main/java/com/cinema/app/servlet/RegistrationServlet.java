package com.cinema.app.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.service.UserService;
import com.cinema.app.utils.Constants;

@WebServlet(Constants.URL_REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_REGISTRATION);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String bankAccount = request.getParameter(Constants.BANK_ACCOUNT);

        boolean correct = conditionsMet(login, password, bankAccount);
        if (correct) {
            UserService userService = new UserService();
            correct = userService.createUser(login, password, bankAccount, Constants.USER);
        }


        if (correct) {
            response.sendRedirect(Constants.JSP_CONGRATULATIONS);
        } else {
            request.setAttribute(Constants.MESSAGE, Constants.ERROR_REGISTRATION);
            doGet(request, response);
        }
    }

    public static boolean conditionsMet(String login, String password, String bankAccount) {
        return !login.trim().isEmpty() && password.matches(Constants.PASSWORD_TERMS) && bankAccount.matches(Constants.BANK_ACCOUNT_TERMS);
    }

}
