package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    // 비밀번호 암호화(해시) 기능을 제공하는 유틸리티 메소드
    public static String hashPassword(String password) {
        // BCrypt.gensalt()로 랜덤 솔트 생성 후, hashpw 로 해시 생성
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User login(String userName, String password) throws Exception {
        // try-with-resources 구문으로 세션 자동 닫기 관리
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            // 1. DB 에서 유저 조회
            User user = mapper.findByUserName(userName);
            // 2. 유저 존재 여부 확인
            if (user == null) {
                throw new Exception("존재하지 않는 사용자입니다.");
            }
            // 3. 비밀번호 검증 (DB의 해시값과 입력된 비번 비교)
            if (!BCrypt.checkpw(password, user.getPasswordHash())) {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            }
            // 4. 검증 완료된 유저 객체 반환
            return user;
        }
    }
}
