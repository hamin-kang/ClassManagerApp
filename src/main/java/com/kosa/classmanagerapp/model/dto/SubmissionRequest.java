package com.kosa.classmanagerapp.model.dto;

public record SubmissionRequest(
        Long submissionId,
        Long assignmentId,
        Long userId,
        Long teamId,
        String content) {
}
