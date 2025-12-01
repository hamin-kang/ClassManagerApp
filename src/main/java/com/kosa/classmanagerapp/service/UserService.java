package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {}

    public List<User> findAll() {
        return users;
    }

    public User findById(long id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public User findByUserName(String user_name) {
        return users.stream()
                .filter(u -> u.getUserName().equals(user_name) )
                .findFirst()
                .orElse(null);
    }
}
