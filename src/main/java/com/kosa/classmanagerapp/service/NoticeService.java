package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.NoticeMapper;
import com.kosa.classmanagerapp.model.Notice;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class NoticeService {
    public List<Notice> findAll(){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            NoticeMapper mapper = session.getMapper(NoticeMapper.class);

            // DB에서 조회
            return mapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
