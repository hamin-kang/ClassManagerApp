package com.kosa.classmanagerapp.dao.submission;

import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.dto.submission.SubmissionRequest;
import com.kosa.classmanagerapp.model.dto.submission.SubmissionStatusResponse;

import java.util.List;

public interface SubmissionMapper {
    Submission findById(Long submissionId);
    List<Submission> findByUserId(Long userId);
    List<SubmissionStatusResponse> findByUserIdIndividualSubmissions(Long userId);
    List<SubmissionStatusResponse> findByUserIdTeamSubmissions(Long userId);
    int save(Submission submission);
    int update(SubmissionRequest submission);


    List<Submission> selectSubmissionSummary();
}
