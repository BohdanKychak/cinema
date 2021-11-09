package com.cinema.app.model;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class UserRoleRequest extends HttpServletRequestWrapper {

    private final String user;
    private final String role;
    private final List<String> roles;
    private final HttpServletRequest realRequest;

    public UserRoleRequest(String user, String role, List<String> roles, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.roles = roles;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (roles == null) {
            return this.realRequest.isUserInRole(role);
        }
        return roles.contains(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }

//         Make an anonymous implementation to just return our user
        return new Principal() {
            @Override
            public String getName() {
                return user;
            }
        };
    }
}