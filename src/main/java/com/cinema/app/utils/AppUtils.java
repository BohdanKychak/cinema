package com.cinema.app.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.cinema.app.model.Account;

public class AppUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<>();
    private static final Map<String, Integer> uri_id_map = new HashMap<>();

    //         Store user information in Session
    public static void storeLoginUser(HttpSession session, Account loginUser) {
//         The JSP can be accessed via $ {loginUser}
        session.setAttribute(Constants.LOGIN_USER, loginUser);
    }

    //       Retrieve the user's information stored in the Session
    public static Account getLoginUser(HttpSession session) {
        return (Account) session.getAttribute(Constants.LOGIN_USER);
    }

    public static int storeRedirectAfterLoginUrl(String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(int redirectId) {
        return id_uri_map.get(redirectId);
    }

}