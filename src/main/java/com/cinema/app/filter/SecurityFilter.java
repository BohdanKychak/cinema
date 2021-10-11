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

import com.cinema.app.request.UserRoleRequestWrapper;
import com.cinema.app.utils.AppUtils;
import com.cinema.app.bean.UserAccount;
import com.cinema.app.utils.SecurityUtils;

@WebFilter("/*")
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
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginUser != null) {
//            User Name
            String userName = loginUser.getUserName();

//            Roles
            List<String> roles = loginUser.getRoles();

//            Old request packet with new Request with userName and Roles information
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

//         Pages requiring login
        if (SecurityUtils.isSecurityPage(request)) {

//             If the user is not logged in yet, redirect to login page.
            if (loginUser == null) {

                String requestUri = request.getRequestURI();

//                 Save the current page for redirection after successful login
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

//             Check the user has a valid role or not
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
