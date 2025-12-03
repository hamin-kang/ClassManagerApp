package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.model.dto.SubmissionStatusResponse;

import java.util.List;
import java.util.Map;

public interface SubmissionMapper {
    List<Submission> findByUserId(Long userId);
    List<SubmissionStatusResponse> findByUserIdIndividualSubmissions(Long userId);
    List<SubmissionStatusResponse> findByUserIdTeamSubmissions(Long userId);
    void save(Submission submission);
}
