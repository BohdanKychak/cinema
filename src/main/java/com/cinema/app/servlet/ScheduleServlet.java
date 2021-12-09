package com.cinema.app.servlet;

import com.cinema.app.dao.ScheduleDAO;
import com.cinema.app.model.SchedulePage;
import com.cinema.app.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static java.lang.String.format;

@WebServlet(Constants.URL_SCHEDULE)
public class ScheduleServlet extends HttpServlet {

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filterAge = getFilter(request);
        String sort = getSortData(request, Constants.SORT, Constants.DEFAULT_SORT);
        String sortOrder = getSortData(request, Constants.SORT_ORDER, Constants.DEFAULT_SORT_ORDER);
        int position = getIntParameter(request, Constants.POSITION);
        int pageSize = getPageSize(request);

        ScheduleDAO scheduleDAO = new ScheduleDAO();

        SchedulePage schedulePage = scheduleDAO.getSchedule(position, pageSize, sort, sortOrder, filterAge);
        request.setAttribute(Constants.SCHEDULE_PAGE, schedulePage);

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_SCHEDULE);
        dispatcher.forward(request, response);
    }

    private int getIntParameter(HttpServletRequest request, String name) {
        int parameter = 0;
        if (request.getParameter(name) != null) {
            try {
                parameter = Integer.parseInt(request.getParameter(name));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(format(Constants.ERROR_PARAMETER_INVALID, name));
            }
        }
        return parameter;
    }

    int getPageSize(HttpServletRequest request) {
        int pageSize = getIntParameter(request, Constants.PAGE_SIZE);
        return pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    private String getSortData(HttpServletRequest request, String name, String defaultSort) {
        return request.getParameter(name) != null ? request.getParameter(name) : defaultSort;
    }

    private String getFilter(HttpServletRequest request) {
        String filterAge = request.getParameter(Constants.FILTER_BY_AGE);
        return Objects.requireNonNullElse(filterAge, Constants.EMPTY);
    }

}
