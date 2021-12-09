package com.cinema.app.servlet;

import com.cinema.app.service.PlaceService;
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
import java.util.Objects;

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

        String message = workWithData(id, place, login);

        request.getSession().setAttribute(Constants.MESSAGE, message);
        if (message.matches(Constants.NUMERIC_TERMS)) {
            response.sendRedirect(Constants.PURCHASED);
        } else if (message.matches(Constants.LIST_TERMS)) {
            request.setAttribute(Constants.MESSAGE2, Constants.MESSAGE);
            doGet(request, response);
        } else {
            doGet(request, response);
        }

    }

    private String workWithData(String id, String place, String login) {

        if (id.matches(Constants.NUMERIC_TERMS)) {
            long sessionId = Long.parseLong(id);

            if (PlaceService.existsId(sessionId)) {

                if (place.matches(Constants.NUMERIC_TERMS)) {

                    int placeNumber = Integer.parseInt(place);
                    if (placeNumber >= Constants.MIN_SEATS_HALL && placeNumber <= Constants.MAX_SEATS_HALL) {
                        String purchase = PurchaseDAO.getPurchaseCode(sessionId, placeNumber, login);
                        return Objects.requireNonNullElse(purchase, Constants.MESSAGE_ACCOUNT);
                    }

                } else {
                    return PlaceService.getInfoPlaces(id);
                }
            }
        }
        return Constants.MESSAGE_EMPTY;
    }

}
