package com.kosa.classmanagerapp.service;

import com.kosa.classmanagerapp.dao.SubmissionMapper;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.dto.SubmissionStatusResponse;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

//제출 관리
public class SubmissionService {
    public SubmissionService() {}

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


}
