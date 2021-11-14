package com.cinema.app.servlet;

import com.cinema.app.utils.Constants;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(Constants.URL_APP_EXCEPTION_HANDLER)
public class AppExceptionHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_APP_EXCEPTION_HANDLER);
        dispatcher.forward(processError(request), response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private HttpServletRequest processError(HttpServletRequest request) {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) request
                .getAttribute(Constants.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request
                .getAttribute(Constants.ERROR_STATUS_CODE);
        String servletName = (String) request
                .getAttribute(Constants.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = Constants.UNKNOWN;
        }
        String requestURI = (String) request
                .getAttribute(Constants.ERROR_REQUEST_URI);
        if (requestURI == null) {
            requestURI = Constants.UNKNOWN;
        }

        request.setAttribute(Constants.STATUS_CODE, statusCode);
        request.setAttribute(Constants.REQUEST_URI, requestURI);
        if (statusCode == 500) {
            request.setAttribute(Constants.SERVLET_NAME, servletName);
            request.setAttribute(Constants.THROWABLE_NAME, throwable.getClass().getName());
            request.setAttribute(Constants.THROWABLE_MESSAGE, throwable.getMessage());
        }
        return request;
    }
}
