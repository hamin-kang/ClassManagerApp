package com.kosa.classmanagerapp.model.attendance;

import com.kosa.classmanagerapp.model.BaseEntity;

import java.time.LocalDate;

public class Attendance extends BaseEntity {
    //User(id)
    private long user_id;
    private LocalDate session_Date;
    private Status status;
}
