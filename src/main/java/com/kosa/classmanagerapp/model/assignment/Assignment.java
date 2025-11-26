package com.kosa.classmanagerapp.model.assignment;

import com.kosa.classmanagerapp.model.BaseEntity;

import java.time.LocalDate;

public class Assignment extends BaseEntity {
    private String title;
    private String content;
    //User(id)
    private long creator_id;
    private AssignmentType assignmentType;
    //Team(id)
    private long team_id;
    private boolean is_close;
    private String presentation_order_team_id;
    private LocalDate due_Date;

}
