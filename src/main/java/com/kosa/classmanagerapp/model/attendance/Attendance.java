package com.kosa.classmanagerapp.model.attendance;

import com.kosa.classmanagerapp.model.BaseEntity;

import java.time.LocalDate;

public class Attendance extends BaseEntity {
    //User(id)
    private Long userId;
    private LocalDate sessionDate;
    private Status status;
}
