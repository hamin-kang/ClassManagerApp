package com.kosa.classmanagerapp.model.dto;

import java.time.LocalDate;

public record SubmissionStatusResponse(Long submittedId, Long assignmentId, Long teamId, Boolean isSubmitted, String assignmentName,
                                       LocalDate dueDate, Boolean isClose) {

}
