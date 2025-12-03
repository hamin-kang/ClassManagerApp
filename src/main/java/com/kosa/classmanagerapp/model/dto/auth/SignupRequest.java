package com.kosa.classmanagerapp.model.dto.auth;

import java.time.LocalDate;

public class SignupRequest { // 회원 가입에 필요한 정보만 담음
    private String userName;
    private String password;
    private String fullName;
    private LocalDate birthday;

    public SignupRequest(String userName, String password, String fullName, LocalDate birthday) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
