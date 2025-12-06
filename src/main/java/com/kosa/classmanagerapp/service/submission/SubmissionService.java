package com.kosa.classmanagerapp.service.submission;

import com.kosa.classmanagerapp.dao.TeamMapper;
import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.dao.submission.SubmissionMapper;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.model.dto.submission.SubmissionRequest;
import com.kosa.classmanagerapp.model.dto.submission.SubmissionStatusResponse;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.model.entity.UserAuthorization;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

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

    public int save(Submission submission){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);
            return mapper.save(submission);
        }
    }
    public int update(SubmissionRequest req){
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true)) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);
            return mapper.update(req);
        }
    }
// admin 화면 총 제출 과제수
    public List<Submission> getSubmissionSummary() {
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            SubmissionMapper mapper = session.getMapper(SubmissionMapper.class);
            return mapper.selectSubmissionSummary();
        }
    }

    public int createSubmissions(SqlSession session, Assignment assignment) {
        SubmissionMapper submissionMapper = session.getMapper(SubmissionMapper.class);
        int count = 0;

        if (assignment.getAssignmentType() == AssignmentType.INDIVIDUAL) {
            List<User> users = session.getMapper(UserMapper.class)
                    .findByAuthorization(UserAuthorization.USER.name());

            for (User u : users) {
                count += submissionMapper.save(
                        Submission.builder()
                                .assignmentId(assignment.getId())
                                .submitterUserId(u.getId())
                                .isSubmitted(false)
                                .build()
                );
            }

        } else {
            List<Team> teams = session.getMapper(TeamMapper.class).findAll();

            for (Team t : teams) {
                count += submissionMapper.save(
                        Submission.builder()
                                .assignmentId(assignment.getId())
                                .teamId(t.getId())
                                .isSubmitted(false)
                                .build()
                );
            }
        }

        return count;
    }

}
