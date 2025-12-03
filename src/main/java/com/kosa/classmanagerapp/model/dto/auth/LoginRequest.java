package com.kosa.classmanagerapp.model.dto.auth;

public class LoginRequest { // 로그인에 필요한 정보만 담음
    private String userName;
    private String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
