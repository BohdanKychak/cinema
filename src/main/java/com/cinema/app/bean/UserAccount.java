package com.cinema.app.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAccount {

    private String userName;
    private String password;

    private List<String> roles;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, String... roles) {
        this.userName = userName;
        this.password = password;

        this.roles = new ArrayList<>();
        if (roles != null) {
            Collections.addAll(this.roles, roles);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}