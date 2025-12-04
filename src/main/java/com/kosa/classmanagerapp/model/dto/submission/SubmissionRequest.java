package com.kosa.classmanagerapp.model.dto.submission;

public record SubmissionRequest(
        Long submissionId,
        Long assignmentId,
        Long userId,
        Long teamId,
        Boolean isSubmitted,
        String content) {
}
