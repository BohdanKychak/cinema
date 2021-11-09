package com.cinema.app.service;

import com.cinema.app.dao.RegistrationDAO;
import com.cinema.app.dao.AccountDAO;
import com.cinema.app.model.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.cinema.app.utils.Constants;

public class UserService {
    private static final Map<String, Account> mapUsers = new HashMap<>();

    static {
        initUsers();
    }

    private static void initUsers() {

        List<String> userLogin = AccountDAO.getLogin(Constants.USER);
        List<String> userPassword = AccountDAO.getPassword(Constants.USER);

        for (int i = 0; i < userLogin.size(); i++) {
            // This user has a role as USER.
            Account userAccount = new Account(userLogin.get(i), userPassword.get(i), Constants.USER, //
                    Constants.ROLE_USER);
            mapUsers.put(userAccount.getLogin(), userAccount);
        }

        List<String> adminLogin = AccountDAO.getLogin(Constants.ADMIN);
        List<String> adminPassword = AccountDAO.getPassword(Constants.ADMIN);
        for (int i = 0; i < adminLogin.size(); i++) {
            // This user has 2 roles USER and ADMIN.
            Account adminAccount = new Account(adminLogin.get(i), adminPassword.get(i), Constants.ADMIN, //
                    Constants.ROLE_USER, Constants.ROLE_ADMIN);
            mapUsers.put(adminAccount.getLogin(), adminAccount);
        }
    }

    // Find a User by login and password.

    public static Account findUser(String login, String password) {
        initUsers();
        Account account = mapUsers.get(login);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    public boolean createUser(String login, String password, String bankAccount, String role) {

        List<String> loginList = AccountDAO.getAllLogin();
        for (String s : loginList) {
            if (Objects.equals(login, s)) {
                return false;
            }
        }
        return RegistrationDAO.getRegistration(login, password, bankAccount, role);
    }
}
