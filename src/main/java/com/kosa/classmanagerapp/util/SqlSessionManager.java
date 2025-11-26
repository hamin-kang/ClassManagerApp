package com.kosa.classmanagerapp.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionManager {
    // SqlSession 객체를 생성해주는 팩토리
    private static SqlSessionFactory sqlSessionFactory;
    // 클래스가 로드될 때(가장 처음에) 딱 한번만 실행됨
    static {
        // 설정 파일 경로: src/main/resources/mybatis-config.xml
        String resource = "mybatis-config.xml";
        Reader reader = null;
        try {
            // 리소스 파일(설정 파일)을 읽어옴
            reader = Resources.getResourceAsReader(resource);

            // SqlSessionFactory 빌드
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            // 연결 성공 시 메시지 추가
            System.out.println("MyBatis 설정 및 DB 연결 성공!");
        } catch (IOException e) {
            // DB 연결 문제 시 메시지 출력
            System.err.println("데이터베이스 연결 실패: " + e.getMessage());
            throw new RuntimeException("데이터베이스 연결에 문제가 있습니다.", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {}
            }
        }
    }

    // SqlSessionFactory 를 반환하는 getter 메서드를 추가하여 테스트에 활용
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }


}
