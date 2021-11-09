package com.cinema.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private String login;
    private String password;
    private String role;
    private final List<String> roles;


    public Account(String login, String password, String role, String... roles) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.roles = new ArrayList<>();
        if (roles != null) {
            Collections.addAll(this.roles, roles);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getRoles() {
        return roles;
    }
}