package com.kosa.classmanagerapp.service;

import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    // 비밀번호 암호화(해시) 기능을 제공하는 유틸리티 메소드
    public static String hashPassword(String password) {
        // BCrypt.gensalt()로 랜덤 솔트 생성 후, hashpw 로 해시 생성
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
