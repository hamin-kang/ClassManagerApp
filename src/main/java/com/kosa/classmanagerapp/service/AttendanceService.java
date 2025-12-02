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
         //   List<Attendance> list = mapper.getAttendance();

//
//            System.out.println("데이터 출력 !!");
//            // 로그 출력
//            for (Attendance a : list) {
//                System.out.println(
//                        "ID: " + a.getAttendanceId() +
//                                ", UserID: " + a.getUserId() +
//                                ", UserName: " + a.getUserName() +
//                                ", Date: " + a.getSessionDate() +
//                                ", Status: " + a.getStatus()
//                );
//            }
//
//            return list;

        }
    }
}



