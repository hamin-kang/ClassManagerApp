package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.entity.User;

public class SessionService {
    private static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}
