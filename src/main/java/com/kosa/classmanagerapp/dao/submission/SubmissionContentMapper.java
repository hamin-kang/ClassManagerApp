package com.kosa.classmanagerapp.dao.submission;

import com.kosa.classmanagerapp.model.SubmissionContent;

public interface SubmissionContentMapper {
    SubmissionContent findById(Long submissionId);
    SubmissionContent findBySubmissionId(Long submissionId);
    int save(SubmissionContent content);
    int update(SubmissionContent submissionContent);
}
