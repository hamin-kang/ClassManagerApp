package com.kosa.classmanagerapp.service.submission;

import com.kosa.classmanagerapp.dao.submission.SubmissionContentMapper;
import com.kosa.classmanagerapp.dao.submission.SubmissionMapper;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.SubmissionContent;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

public class SubmissionContentService {
    public SubmissionContentService() {}
    public SubmissionContent findBySubmissionId(Long submissionId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionContentMapper mapper = session.getMapper(SubmissionContentMapper.class);

            // DB에서 조회
            return mapper.findBySubmissionId(submissionId);
        }
    }
    public SubmissionContent findById(Long submissionId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionContentMapper mapper = session.getMapper(SubmissionContentMapper.class);

            // DB에서 조회
            return mapper.findById(submissionId);
        }
    }
    public int save(SubmissionContent submissionContent){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            SubmissionContentMapper mapper = session.getMapper(SubmissionContentMapper.class);
            return mapper.save(submissionContent);
        }
    }
    public int update(SubmissionContent submissionContent){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            SubmissionContentMapper mapper = session.getMapper(SubmissionContentMapper.class);
            return mapper.update(submissionContent);
        }
    }
}
