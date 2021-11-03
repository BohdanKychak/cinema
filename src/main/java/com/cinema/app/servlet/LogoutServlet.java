package com.cinema.app.servlet;

import com.cinema.app.utils.Constants;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(Constants.URL_LOGOUT)
public class LogoutServlet extends HttpServlet {

    public LogoutServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();

//         Redirect to Homepage
        response.sendRedirect(request.getContextPath() + Constants.JSP_INDEX);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doGet(request, response);
    }

}
