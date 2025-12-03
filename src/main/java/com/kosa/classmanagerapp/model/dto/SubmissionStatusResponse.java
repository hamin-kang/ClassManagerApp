package com.kosa.classmanagerapp.model.dto;

import java.time.LocalDate;

public class SubmissionStatusResponse {
    private final Long submittedId;
    private final Long assignmentId;
    private final Boolean isSubmitted;
    private final String assignmentName;
    private final LocalDate dueDate;

    public SubmissionStatusResponse(Long submittedId,
                                    Long assignmentId,
                                    Boolean isSubmitted,
                                    String assignmentName,
                                    LocalDate dueDate) {
        this.submittedId = submittedId;
        this.assignmentId = assignmentId;
        this.isSubmitted = isSubmitted;
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
    }

    public Long getSubmittedId(){
        return submittedId;
    }

    public Long getAssignmentId(){ return  assignmentId; }
    public Boolean getSubmitted() {
        return isSubmitted;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
