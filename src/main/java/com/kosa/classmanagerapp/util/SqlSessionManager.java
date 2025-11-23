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
        // 설정 파일 경로: src/main/resources/config/mybatis-config.xml
        String resource = "config/mybatis-config.xml";
        Reader reader = null;
        try {
            // 리소스 파일(설정 파일)을 읽어옴
            reader = Resources.getResourceAsReader(resource);

            // SqlSessionFactory 빌드
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            // 파일을 찾지 못하거나 읽지 못하면 에러 출력 후 애플리케이션 종료
            throw new RuntimeException("MyBatis 설정 파일을 로드하지 못했습니다: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {}
            }
        }
    }


}
