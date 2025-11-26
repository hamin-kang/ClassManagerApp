package com.kosa.classmanagerapp.model;

import java.time.LocalDate;

public class Submission extends BaseEntity{
    //Assignment(id)
    private long assignment_id;
    //User(id)
    private long submitter_user_id;
    private String content;
    private LocalDate submitted_ad;

}
