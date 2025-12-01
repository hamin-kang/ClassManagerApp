package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.ProjectMapper;
import com.kosa.classmanagerapp.dao.SubmissionMapper;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.global.initData.InitDataMemory;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

//제출 관리
public class SubmissionService {

    private final List<Submission> submissions = new ArrayList<>();

    public SubmissionService() {}

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

    public void save(Submission submission){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);
            mapper.save(submission);
        }
    }


}
