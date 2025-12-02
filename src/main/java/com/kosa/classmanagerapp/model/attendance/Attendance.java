package com.kosa.classmanagerapp.model.attendance;

import com.kosa.classmanagerapp.model.BaseEntity;
import com.mysql.cj.conf.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Attendance extends BaseEntity {

    private int attendanceId;    // ← 정확한 필드명
    private Long userId;
    private String userName;
    private LocalDate sessionDate;
    private Status status;


    // --- 생성자 -
    public Attendance() {}

    public Attendance(int attendanceId, Long userId, String userName, LocalDate sessionDate, Status status) {
        this.attendanceId = attendanceId;
        this.userId = userId;
        this.userName = userName;
        this.sessionDate = sessionDate;
        this.status = status;
    }


    // ---- Getter / Setter ----

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


//enum
    public enum Status {
        PRESENT,
        ABSENT,
        LEAVE_EARLY
    }

}
