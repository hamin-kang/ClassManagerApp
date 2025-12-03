package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.entity.User;

import java.util.List;

// DB에 접근할 메소드를 정의하는 인터페이스
public interface UserMapper {
    // 아이디로 회원 정보 조회
    User findByUserName(String userName);
    // 회원가입 용 INSERT 메소드
    int save(User user);
    int updateTeam(User user);

    //모든 사용자 조회 (팀 생성용)
    List<User> findAllUser();

    User findUserById(long userId);
}
