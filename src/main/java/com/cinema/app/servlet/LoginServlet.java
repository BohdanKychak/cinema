package com.cinema.app.servlet;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.model.UserAccount;
import com.cinema.app.service.UserService;
import com.cinema.app.utils.AppUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserAccount userAccount = UserService.findUser(login, password);

        if (userAccount == null) {
            String errorMessage = "Invalid login or Password";

            request.setAttribute("errorMessage", errorMessage);

            doGet(request, response);
            return;
        }

        AppUtils.storeLoginUser(request.getSession(), userAccount);

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(redirectId);
        response.sendRedirect(Objects.requireNonNullElseGet(requestUri, () -> request.getContextPath() + "/userInfo"));

    }

}
