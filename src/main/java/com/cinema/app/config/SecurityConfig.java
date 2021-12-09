package com.cinema.app.config;

import com.cinema.app.utils.Constants;

import java.util.*;

public class SecurityConfig {

    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        // Configuration for the role "USER".
        List<String> urlPatternsUser = new ArrayList<>();

        urlPatternsUser.add(Constants.URL_INFO);
        urlPatternsUser.add(Constants.URL_USER);
        urlPatternsUser.add(Constants.URL_PURCHASE);
        urlPatternsUser.add(Constants.URL_PURCHASED);

        mapConfig.put(Constants.ROLE_USER, urlPatternsUser);

        // Configuration for the role "ADMIN".
        List<String> urlPatternsAdmin = new ArrayList<>();

        urlPatternsAdmin.add(Constants.URL_INFO);
        urlPatternsAdmin.add(Constants.URL_USER);
        urlPatternsAdmin.add(Constants.URL_ADMIN);
        urlPatternsAdmin.add(Constants.URL_PURCHASE);
        urlPatternsAdmin.add(Constants.URL_PURCHASED);
        urlPatternsAdmin.add(Constants.URL_CREATE_ACCOUNT);
        urlPatternsAdmin.add(Constants.URL_SCHEDULE_CHANGES);

        mapConfig.put(Constants.ROLE_ADMIN, urlPatternsAdmin);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
