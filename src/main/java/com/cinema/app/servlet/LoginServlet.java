package com.cinema.app.servlet;

import com.cinema.app.model.Account;
import com.cinema.app.service.UserService;
import com.cinema.app.utils.AppUtils;
import com.cinema.app.utils.Constants;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(Constants.URL_LOGIN)
public class LoginServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher(Constants.JSP_LOGIN);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter(Constants.LOGIN);
        String password = request.getParameter(Constants.PASSWORD);
        Account account = UserService.findUser(login, Constants.EMPTY + password.hashCode());

        if (account == null) {
            request.setAttribute(Constants.MESSAGE, Constants.LOGIN);
            doGet(request, response);
            return;
        }

        AppUtils.storeLoginUser(request.getSession(), account);

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter(Constants.REDIRECT_ID));
        } catch (Exception e) {
            log.info(e.getMessage() + " redirectId is null");
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(redirectId);
        response.sendRedirect(Objects.requireNonNullElseGet(requestUri, () -> request.getContextPath() + Constants.URL_INFO));

    }

}
