package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.Submission;

import java.util.List;

public interface SubmissionMapper {
    List<Submission> findByUserId(Long userId);
    void save(Submission submission);
}
