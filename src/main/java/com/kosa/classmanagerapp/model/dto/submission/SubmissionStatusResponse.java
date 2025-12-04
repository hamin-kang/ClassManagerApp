package com.kosa.classmanagerapp.model.dto.submission;

import java.time.LocalDateTime;

public record SubmissionStatusResponse(Long submissionId, Long assignmentId,Long submitterUserId, Long teamId, Boolean isSubmitted, String assignmentName,
                                       LocalDateTime dueDate, Boolean isClose) {

}
