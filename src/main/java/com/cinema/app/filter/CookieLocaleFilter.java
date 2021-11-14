package com.cinema.app.filter;

import com.cinema.app.utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = Constants.COOKIE_LOCALE_FILTER, urlPatterns = {Constants.URL_ANY})
public class CookieLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getParameter(Constants.COOKIE_LOCALE) != null) {
            Cookie cookie = new Cookie(Constants.LANG, req.getParameter(Constants.COOKIE_LOCALE));
            res.addCookie(cookie);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) {
    }

}
