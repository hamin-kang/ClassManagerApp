package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.attendance.Attendance;

import java.util.List;

public interface AttendanceMapper {
    List<Attendance> getAttendance();
    List<Attendance> selectAttendanceRanking();

}
