package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.global.initData;

import java.util.ArrayList;
import java.util.List;

//제출 관리
public class SubmissionService {

    private final List<Submission> submissions = new ArrayList<>();

    public SubmissionService() {
        submissions.addAll(initData.createDummySubmissions());
    }

    public List<Submission> findByUserId(long userId) {
        return submissions.stream()
                .filter(s -> s.getSubmitterUserId() == userId)
                .toList();
    }

    public List<Submission> findByAssignmentId(long assignmentId) {
        return submissions.stream()
                .filter(s -> s.getAssignmentId() == assignmentId)
                .toList();
    }



}
