package com.cinema.app.dao;

import java.util.HashMap;
import java.util.Map;
import com.cinema.app.config.SecurityConfig;
import com.cinema.app.bean.UserAccount;


public class UserDAO {

    private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();

    static {
        initUsers();
    }

    private static void initUsers() {

        // This user has a role as USER.
        UserAccount emp = new UserAccount("user", "123", //
                SecurityConfig.ROLE_USER);


        // This user has 2 roles USER and ADMIN.
        UserAccount mng = new UserAccount("admin", "789", //
                SecurityConfig.ROLE_USER, SecurityConfig.ROLE_ADMIN);


        mapUsers.put(emp.getUserName(), emp);
        mapUsers.put(mng.getUserName(), mng);
    }

    // Find a User by userName and password.
    public static UserAccount findUser(String userName, String password) {
        UserAccount u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

}
