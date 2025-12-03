package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.User;

// DB에 접근할 메소드를 정의하는 인터페이스
public interface UserMapper {
    // 아이디로 회원 정보 조회
    User findByUserName(String userName);
    // 회원가입 용 INSERT 메소드
    int save(User user);
    int updateTeam(User user);

}
