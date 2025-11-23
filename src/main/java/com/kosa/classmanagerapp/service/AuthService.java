package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.Users;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

public class AuthService {
    public boolean registerUser(String user_name, String full_name, String password_hash) {
        return true;
    }
}
