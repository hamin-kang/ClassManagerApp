package com.kosa.classmanagerapp.model.dto.submission;

//디테일페이지 제출 요청
public record SubmissionRequest(
        Long submissionId,
        Long assignmentId,
        Long userId,
        Long teamId,
        Boolean isSubmitted,
        String content) {
}
