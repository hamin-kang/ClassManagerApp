package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.global.initData;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    // 회원가입
    public boolean save(User user, String password) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            // 아이디 중복 체크
            if (userMapper.findByUserName(user.getUserName()) != null) {
                return false; // 이미 존재하는 아이디
            }
            // 비밀번호 암호화
            String passwordHash = AuthService.hashPassword(password);
            user.setPasswordHash(passwordHash);
            // DB 저장
            int result = userMapper.save(user);
            session.commit(); // 커밋 필수
            return result > 0;
        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
            return false;
        }
    }



    // 더미데이터
    private final List<User> users = new ArrayList<>();

    public UserService() {
        // initData 에서 더미 데이터 로드
        System.out.println("User Dummy Create");
        users.addAll(initData.createDummyUsers());
    }

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
