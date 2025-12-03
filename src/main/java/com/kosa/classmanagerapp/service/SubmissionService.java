package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.SubmissionMapper;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.dto.SubmissionRequest;
import com.kosa.classmanagerapp.model.dto.SubmissionStatusResponse;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//제출 관리
public class SubmissionService {
    public SubmissionService() {}
    public Submission findById(Long submissionId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);

            // DB에서 조회
            return mapper.findById(submissionId);
        }
    }
    public List<Submission> findByUserId(Long userId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);

            // DB에서 조회
            return mapper.findByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public List<SubmissionStatusResponse> findByUserIdIndividualSubmissions(Long userId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);

            // DB에서 조회
            return mapper.findByUserIdIndividualSubmissions(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SubmissionStatusResponse> findByUserIdTeamSubmissions(Long userId) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);

            // DB에서 조회
            return mapper.findByUserIdTeamSubmissions(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void save(Submission submission){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);
            mapper.save(submission);
        }
    }

    public int submitAssignment(SubmissionRequest req) {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);

            // 1) 기존 제출물 조회
            Submission existing = mapper.findById(
                    req.submissionId()
            );

            if (existing == null) {
                // 2) 없으면 INSERT

                Submission newSubmission = new Submission.Builder()
                        .assignmentId(req.assignmentId())
                        .submitterUserId(req.userId())
                        .teamId(req.teamId())  // INDIVIDUAL 이면 null, TEAM이면 팀ID 넣기
                        .content(req.content())
                        .submittedAt(LocalDateTime.now())
                        .build();
                return mapper.save(newSubmission);
            }

            // 3) 있으면 UPDATE
            existing.setContent(req.content());
            existing.setSubmittedAt(LocalDateTime.now());

            return mapper.update(existing);
        }
    }

}
