package com.cinema.app.servlet;

import com.cinema.app.dao.PlaceDAO;
import com.cinema.app.dao.PurchaseDAO;
import com.cinema.app.utils.AppUtils;
import com.cinema.app.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(Constants.URL_PURCHASE)
public class PurchaseServlet extends HttpServlet {
    public PurchaseServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher(Constants.JSP_PURCHASE);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter(Constants.ID);
        String place = request.getParameter(Constants.PLACE);
        String login = AppUtils.getLoginUser(request.getSession()).getLogin();

        String message = null;

        if (id.matches(Constants.NUMERIC_TERMS)) {
            message = workWithData(id, place, login);
        }

        if (message == null) {
            message = Constants.ERROR_EMPTY;
        }

        request.setAttribute(Constants.MESSAGE, message);
        doGet(request, response);
    }

    private String workWithData(String id, String place, String login) {
        long sessionId = Long.parseLong(id);
        if (PlaceDAO.existsId(sessionId)) {
            if (place.matches(Constants.NUMERIC_TERMS)) {
                int placeNumber = Integer.parseInt(place);
                if (placeNumber < Constants.MIN_SEATS_HALL || placeNumber > Constants.MAX_SEATS_HALL) {
                    return null;
                }
                return PurchaseDAO.getPurchaseCode(sessionId, place, login);
            } else {
                return PlaceDAO.getMessageAboutPlace(id);
            }
        }
        return null;
    }

}
