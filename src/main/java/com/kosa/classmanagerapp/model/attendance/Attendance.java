package com.kosa.classmanagerapp.model.attendance;

import com.kosa.classmanagerapp.model.BaseEntity;

import java.time.LocalDate;

public class Attendance extends BaseEntity {
    //User(id)

    private int attendancedId;
    private Long userId;
    private String userName;
    private LocalDate sessionDate;
    private Status status;
}
