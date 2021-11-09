package com.cinema.app.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinema.app.model.UserRoleRequest;
import com.cinema.app.utils.AppUtils;
import com.cinema.app.model.Account;
import com.cinema.app.utils.Constants;
import com.cinema.app.utils.SecurityUtils;

@WebFilter(Constants.URL_ANY)
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

//      User information stored in Session
//      (After successful login)
        Account loginUser = AppUtils.getLoginUser(request.getSession());

        if (servletPath.equals(Constants.LOGIN)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginUser != null) {
//            Login
            String login = loginUser.getLogin();

//            Role
            String role = loginUser.getRole();

//            Roles
            List<String> roles = loginUser.getRoles();

//            Old request packet with new Request with login and Roles information
            wrapRequest = new UserRoleRequest(login, role, roles, request);
        }

//         Pages requiring login
        if (SecurityUtils.isSecurityPage(request)) {

//             If the user is not logged in yet, redirect to login page.
            if (loginUser == null) {

                String requestUri = request.getRequestURI();

//                 Save the current page for redirection after successful login
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + Constants.LOGIN_REDIRECT_ID + redirectId);
                return;
            }

//             Check the user has a valid role or not
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher(Constants.JSP_ACCESS_DENIED);

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) {

    }

}
