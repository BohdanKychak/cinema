package com.cinema.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cinema.app.config.SecurityConfig;
import com.cinema.app.bean.UserAccount;


public class UserDAO {

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

}
