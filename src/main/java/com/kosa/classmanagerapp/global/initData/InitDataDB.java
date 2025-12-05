package com.kosa.classmanagerapp.global.initData;

import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.*;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.model.entity.UserAuthorization;
import com.kosa.classmanagerapp.service.*;
import com.kosa.classmanagerapp.service.submission.SubmissionService;
import com.kosa.classmanagerapp.service.auth.UserService;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InitDataDB {

    private final ProjectService projectService = AppContext.PROJECT_SERVICE;
    private final TeamService teamService = AppContext.TEAM_SERVICE;
    private final UserService userService = AppContext.USER_SERVICE;
    private final AssignmentService assignmentService = AppContext.ASSIGNMENT_SERVICE;
    private final SubmissionService submissionService = AppContext.SUBMISSION_SERVICE;

    public void initAll() {
        createDummyProjects();
        createDummyUsers();
        createDummyTeams();
        updateDummyUsers();

        createDummyAssignments();
        createDummySubmissions();

        System.out.println("==== InitDataDB: 전체 더미 데이터 DB 삽입 완료 ====");
    }

    // -----------------------------
    // 1) 프로젝트 생성
    // -----------------------------
    public void createDummyProjects() {
        Project p1 = Project.builder()
                .projectName("1차프로젝트")
                .build();

        projectService.save(p1);
    }

    public void createDummyUsers() {
        User admin = User.builder()
                .userName("admin")
                .passwordHash("$2a$10$H1.xHwCVXknsbx2LccFDsuNyZ4hw/G9t29yzlA0o9E/Dfw489ZWlC")
                .fullName("관리자")
                .teamId(null)
                .birthday(LocalDate.of(2000, 1, 1))
                .authorization(UserAuthorization.ADMIN)
                .build();
        User user1 = User.builder()
                .userName("user1")
                .passwordHash("$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW")
                .fullName("user1")
                .teamId(null)
                .birthday(LocalDate.of(2000, 2, 8))
                .authorization(UserAuthorization.USER)
                .build();
        User user2 = User.builder()
                .userName("user2")
                .passwordHash("$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW")
                .fullName("user2")
                .teamId(null)
                .birthday(LocalDate.of(2000, 2, 8))
                .authorization(UserAuthorization.USER)
                .build();
        User user3 = User.builder()
                .userName("user3")
                .passwordHash("$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW")
                .fullName("user3")
                .teamId(null)
                .birthday(LocalDate.of(2000, 2, 8))
                .authorization(UserAuthorization.USER)
                .build();
        User user4 = User.builder()
                .userName("user4")
                .passwordHash("$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW")
                .fullName("user4")
                .teamId(null)
                .birthday(LocalDate.of(2000, 2, 8))
                .authorization(UserAuthorization.USER)
                .build();
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(false)) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            userMapper.save(admin);
            userMapper.save(user1);
            userMapper.save(user2);
            userMapper.save(user3);
            userMapper.save(user4);

            session.commit();

            System.out.println("User Dummy Insert 성공");

        } catch (Exception e) {
            System.err.println("회원가입에 실패했습니다: " + e.getMessage());
            e.printStackTrace();
        }

    }
    // -----------------------------
    // 2) 팀 생성 (1팀, 2팀)
    // -----------------------------
    public void createDummyTeams() {

        Team team1 = Team.builder()
                .teamName("1팀")
                .projectId(1L)
                .build();

        Team team2 = Team.builder()
                .teamName("2팀")
                .projectId(1L)
                .build();

        teamService.save(team1);
        teamService.save(team2);
    }

    // -----------------------------
    // 3) 유저 팀 배정
    // -----------------------------
    public void updateDummyUsers() {

        for (long i = 2; i <= 5; i++) {

            User user = new User();
            user.setId(i);
            user.setUserName("user" + (i - 1));
            user.setBirthday(LocalDate.now());
            user.setAuthorization(UserAuthorization.USER);

            // 2번, 3번 → 1팀 / 4번, 5번 → 2팀
            long teamId = (i <= 3) ? 1L : 2L;
            user.setTeamId(teamId);

            userService.updateTeam(user);
        }
    }

    // -----------------------------
    // 4) 과제 생성 (개인 2개, 팀 2개)
    // -----------------------------
    public void createDummyAssignments() {

        Assignment a1 = Assignment.builder()
                .title("개인 과제 1")
                .content("개인 과제 1 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.INDIVIDUAL)
                .isClose(true)
                .dueDate(LocalDateTime.now().plusDays(3))
                .build();

        Assignment a2 = Assignment.builder()
                .title("개인 과제 2")
                .content("개인 과제 2 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.INDIVIDUAL)
                .isClose(false)
                .dueDate(LocalDateTime.now().plusDays(3))
                .build();

        Assignment a3 = Assignment.builder()
                .title("팀 과제 1")
                .content("팀 과제 1 내용")
                .creatorId(1L)
                .presentationOrderTeamId("1,2")
                .assignmentType(AssignmentType.TEAM)
                .isClose(true)
                .dueDate(LocalDateTime.now().plusDays(7))
                .build();

        Assignment a4 = Assignment.builder()
                .title("팀 과제 2")
                .content("팀 과제 2 내용")
                .creatorId(1L)
                .presentationOrderTeamId("1,2")
                .assignmentType(AssignmentType.TEAM)
                .isClose(false)
                .dueDate(LocalDateTime.now().plusDays(7))
                .build();

        assignmentService.save(a1);
        assignmentService.save(a2);
        assignmentService.save(a3);
        assignmentService.save(a4);
    }

    // -----------------------------
    // 5) 제출물 생성
    // -----------------------------
    public void createDummySubmissions() {

        // ---------- 개인 과제 ----------
        createPersonalSubmission(1L);
        createPersonalSubmission(2L);

        // ---------- 팀 과제 ----------
        createTeamSubmission(3L);  // 팀 과제 1
        createTeamSubmission(4L);  // 팀 과제 2
    }

    // 개인 과제 제출물 생성
    private void createPersonalSubmission(Long assignmentId) {
        for (long userId = 2; userId <= 5; userId++) {
            Submission submission = new Submission.Builder()
                    .assignmentId(assignmentId)
                    .submitterUserId(userId)
                    .isSubmitted(false)
                    .build();

            submissionService.save(submission);
        }
    }

    // 팀 과제 제출물 생성 (팀당 1개만)
    private void createTeamSubmission(Long assignmentId) {

        // 팀 1
        Submission s1 = new Submission.Builder()
                .assignmentId(assignmentId)
                .teamId(1L)
                .isSubmitted(false)
                .build();
        submissionService.save(s1);

        // 팀 2
        Submission s2 = new Submission.Builder()
                .assignmentId(assignmentId)
                .teamId(2L)
                .isSubmitted(false)
                .build();
        submissionService.save(s2);
    }
}
