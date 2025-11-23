package com.kosa.classmanagerapp.model;

public class Users {
    private Integer user_id; // DB 관리용 번호, 앱에서 입력했을 때는 null 이고 DB 에서 자동 생성
    private String user_name; // 아이디
    private String password_hash; // 비밀번호
    private String full_name; // 이름

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getName() {
        return full_name;
    }

    public void setName(String full_name) {
        this.full_name = full_name;
    }
}
