package com.cinema.app.servlet;

import com.cinema.app.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.URL_CREATE_ACCOUNT)
public class CreateAccountServlet extends HttpServlet {

    public CreateAccountServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_CREATE_ACCOUNT);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        String bankAccount = request.getParameter(Constants.BANK_ACCOUNT);
        String role = request.getParameter(Constants.ROLE);

        boolean correct = RegistrationServlet.isDone(login, password, bankAccount, role);

        if (correct) {
            response.sendRedirect(Constants.JSP_DONE);
        } else {
            request.setAttribute(Constants.MESSAGE, Constants.MESSAGE_REGISTRATION);
            doGet(request, response);
        }
    }

}