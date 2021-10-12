package com.cinema.app.utils;

import java.util.List;
import java.util.Objects;

public class SearchForRegistrationErrorsUtils {

    public static String message(String login, String password) {
        if (Objects.equals(login, "") || Objects.equals(password, "")) {
            return "Error! One of the fields is empty";
        }
        List<String> loginList = AccountUtils.getAllLogin();
        for (String s : loginList) {
            if (Objects.equals(login, s)) {
                return "Error! Such a user exists";
            }
        }
        return null;
    }

}
