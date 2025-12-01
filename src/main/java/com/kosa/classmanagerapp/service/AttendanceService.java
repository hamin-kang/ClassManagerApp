package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.AttendanceMapper;
import com.kosa.classmanagerapp.model.attendance.Attendance;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AttendanceService {

    public List<Attendance> getAttendanceList() {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            AttendanceMapper mapper = session.getMapper(AttendanceMapper.class);
             return mapper.getAttendance();




        }
    }
}

// LIST값 로그 찍기

