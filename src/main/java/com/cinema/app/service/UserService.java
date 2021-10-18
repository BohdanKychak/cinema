package com.cinema.app.service;

import com.cinema.app.config.SecurityConfig;
import com.cinema.app.dao.RegistrationDAO;
import com.cinema.app.dao.AccountDAO;
import com.cinema.app.model.UserAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserService {
    private static final Map<String, UserAccount> mapUsers = new HashMap<>();

    static {
        initUsers();
    }

    private static void initUsers() {

        String role = "user";
        List<String> userLogin = AccountDAO.getLogin(role);
        List<String> userPassword = AccountDAO.getPassword(role);

        for (int i = 0; i < userLogin.size(); i++) {
            // This user has a role as USER.
            UserAccount emp = new UserAccount(userLogin.get(i), userPassword.get(i), //
                    SecurityConfig.ROLE_USER);
            mapUsers.put(emp.getLogin(), emp);
        }

        role = "admin";
        List<String> adminLogin = AccountDAO.getLogin(role);
        List<String> adminPassword = AccountDAO.getPassword(role);
        for (int i = 0; i < adminLogin.size(); i++) {
            // This user has 2 roles USER and ADMIN.
            UserAccount mng = new UserAccount(adminLogin.get(i), adminPassword.get(i), //
                    SecurityConfig.ROLE_USER, SecurityConfig.ROLE_ADMIN);
            mapUsers.put(mng.getLogin(), mng);
        }
    }

    // Find a User by login and password.

    public static UserAccount findUser(String login, String password) {
        UserAccount userAccount = mapUsers.get(login);
        if (userAccount != null && userAccount.getPassword().equals(password)) {
            return userAccount;
        }
        return null;
    }

    public boolean createUser(String login, String password, String role) {

        List<String> loginList = AccountDAO.getAllLogin();
        for (String s : loginList) {
            if (Objects.equals(login, s)) {
                return false;
            }
        }
        return RegistrationDAO.getRegistration(login, password, role);
    }
}
