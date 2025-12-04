package com.kosa.classmanagerapp.service.auth;

import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.dto.auth.ChangePasswordRequest;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;
import com.kosa.classmanagerapp.model.dto.auth.SignupRequest;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

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


    public void changePassword(ChangePasswordRequest request) throws Exception {
        // SqlSession 을 수동으로 관리
        SqlSession session = null;
        try {
            // openSession()을 호출하면 기본적으로 auto-commit=false (수동 커밋)로 열림
            session = SqlSessionManager.getSqlSessionFactory().openSession();

            UserMapper mapper = session.getMapper(UserMapper.class);

            // DB 에서 현재 유저 정보 조회 및 검증 로직
            User user = mapper.findUserById(request.getId());
            if (user == null || user.getPasswordHash() == null) {
                throw new Exception("사용자 비밀번호 정보를 찾을 수 없습니다. (DB 오류)");
            }

            if (!BCrypt.checkpw(request.getOldPassword(), user.getPasswordHash())) {
                throw new Exception("현재 비밀번호가 일치하지 않습니다.");
            }

            // 새 비밀번호 암호화 및 업데이트
            String newHash = AuthService.hashPassword(request.getNewPassword());
            int rowsAffected = mapper.updatePassword(user.getId(), newHash);

            if (rowsAffected == 0) {
                // 이 예외는 WHERE id=#{id}가 유저를 찾지 못했다는 뜻입니다.
                throw new Exception("비밀번호 변경 실패: 데이터베이스에 사용자 정보가 없거나 ID 오류입니다.");
            }

            // UPDATE 쿼리가 성공적으로 실행된 후, DB에 영구적으로 저장합니다.
            session.commit();

        } catch (Exception e) {
            // 오류 발생 시 롤백 (변경 사항 취소)
            if (session != null) {
                session.rollback();
            }
            throw e; // Controller 로 예외를 다시 던져서 사용자에게 실패를 알립니다.
        } finally {
            // 세션 닫기
            if (session != null) {
                session.close();
            }
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
