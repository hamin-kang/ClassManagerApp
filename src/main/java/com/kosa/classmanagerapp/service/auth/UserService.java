package com.kosa.classmanagerapp.service.auth;

import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;
import com.kosa.classmanagerapp.model.dto.auth.SignupRequest;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.jdbc.SqlRunner;
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


    public User findById(long id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User findByUserName(String user_name) {
        return users.stream()
                .filter(u -> u.getUserName().equals(user_name))
                .findFirst()
                .orElse(null);
    }

    // DTO 를 받아서 처리(회원가입)
    public boolean signup(SignupRequest request) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            // 아이디 중복 체크
            if (userMapper.findByUserName(request.getUserName()) != null) {
                return false;
            }
            // DTO -> Entity 변환
            User user = new User();
            user.setUserName(request.getUserName());
            user.setFullName(request.getFullName());
            user.setBirthday(request.getBirthday());
            // 비밀번호 암호화
            String passwordHash = AuthService.hashPassword(request.getPassword());
            user.setPasswordHash(passwordHash);
            // DB 저장
            int result = userMapper.save(user);
            session.commit();
            return result > 0;
        } catch (Exception e) {
            System.err.println("회원가입에 실패했습니다" + e.getMessage());
            return false;
        }
    }

    public int updateTeam(User user) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            int result = mapper.updateTeam(user);
            session.commit();
            return result;
        }
    }

    //user테이블 가져오기
    public List<User> findAllUser() {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findAllUser();
        }
    }


    public User findUserById(long userId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findUserById(userId); // Mapper 인터페이스 사용
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int updateUserTeam(Long userId, int teamId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            int result = mapper.updateUserTeam(userId, teamId);
            session.commit();
            return result;
        }
    }

}
