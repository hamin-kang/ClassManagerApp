package com.kosa.classmanagerapp;

import org.mindrot.jbcrypt.BCrypt;

public class HashGeneratorTest {
    public static void main(String[] args) {
        String plainPassword = "1234";

        // BCrypt.hashpw를 사용해 60자 해시를 생성.
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        System.out.println("=================================================");
        System.out.println("생성된 유효한 60자 해시 값");
        System.out.println("길이: " + hashedPassword.length() + " 자");
        System.out.println("해시: " + hashedPassword);
        System.out.println("=================================================");
    }
}
