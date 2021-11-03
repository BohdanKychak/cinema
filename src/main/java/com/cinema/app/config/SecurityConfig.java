package com.cinema.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cinema.app.utils.Constants;

public class SecurityConfig {

    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        // Configuration for the role "USER".
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add(Constants.URL_INFO);
        urlPatterns1.add(Constants.URL_USER);

        mapConfig.put(Constants.ROLE_USER, urlPatterns1);

        // Configuration for the role "ADMIN".
        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add(Constants.URL_INFO);
        urlPatterns2.add(Constants.URL_USER);
        urlPatterns2.add(Constants.URL_ADMIN);
        urlPatterns2.add(Constants.URL_CREATE_ACCOUNT);
        urlPatterns2.add(Constants.URL_SCHEDULE_CHANGES);

        mapConfig.put(Constants.ROLE_ADMIN, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}
