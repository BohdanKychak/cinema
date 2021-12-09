package com.cinema.app.filter;

import com.cinema.app.utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(Constants.URL_ANY)
public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(Constants.UTF8);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}