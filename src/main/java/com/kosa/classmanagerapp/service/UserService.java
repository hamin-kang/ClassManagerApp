package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {
        // initData에서 더미 데이터 로드
        System.out.println("User Dummy Create");
        users.addAll(InitDataMemory.createDummyUsers());
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
    
    // 회원가입 저장
    public boolean save(User user, String password) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            // 아이디 중복 체크
            if (mapper.findByUserName(user.getUserName()) != null) {
                return false; // 이미 존재하는 아이디
            }

            // 비밀번호 암호화 (AuthService 활용)
            String hashedPassword = AuthService.hashPassword(password);
            user.setPasswordHash(hashedPassword);

            // DB 저장
            int result = mapper.save(user);
            session.commit(); // 커밋 필수!

            return result > 0;
        } catch (Exception e) {
            System.err.println("회원가입에 실패했습니다" + e.getMessage());
            return false;
        }
    }
}
