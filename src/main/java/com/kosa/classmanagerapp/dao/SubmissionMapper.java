package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.dto.SubmissionStatusResponse;

import java.util.List;

public interface SubmissionMapper {
    Submission findById(Long submissionId);
    List<Submission> findByUserId(Long userId);
    List<SubmissionStatusResponse> findByUserIdIndividualSubmissions(Long userId);
    List<SubmissionStatusResponse> findByUserIdTeamSubmissions(Long userId);
    int save(Submission submission);
    int update(Submission submission);
}
