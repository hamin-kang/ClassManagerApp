package com.kosa.classmanagerapp.global.initData;

import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.*;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InitDataDB {

    private final ProjectService projectService = AppContext.PROJECT_SERVICE;
    private final TeamService teamService = AppContext.TEAM_SERVICE;
    private final UserService userService = AppContext.USER_SERVICE;
    private final AssignmentService assignmentService = AppContext.ASSIGNMENT_SERVICE;
    private final SubmissionService submissionService = AppContext.SUBMISSION_SERVICE;


    // 실행 시 전체 초기 데이터 넣는 메서드
    public void initAll() {
        createDummyProjects();
        createDummyUsers();
        createDummyTeams();
        createDummyAssignments();
        createDummySubmissions();
        System.out.println("==== InitDataDB: 전체 더미 데이터 DB에 삽입 완료 ====");
    }

    // 1) 프로젝트
    public void createDummyProjects() {
        Project p1 = Project.builder()
                .projectName("1차프로젝트")
                .build();

        projectService.save(p1);
    }

    // 2) 팀
    public void createDummyTeams() {

        Team team1 = Team.builder()
                .teamName("1팀")
                .projectId(1L)
                .leaderId(2L)
                .build();

        Team team2 = Team.builder()
                .teamName("2팀")
                .projectId(1L)
                .leaderId(4L)
                .build();

        teamService.save(team1);
        teamService.save(team2);
    }

    // 3) 유저
    public void createDummyUsers() {
//
//        // admin
//        User admin = new User();
//        admin.setId(1L);
//        admin.setUserName("admin");
//        admin.setBirthday(LocalDate.now());
//        admin.setAuthorization(UserAuthorization.ADMIN);
//        userService.save(admin);
//
//        // 일반 유저 4명
//        for (long i = 2; i <= 5; i++) {
//            User user = new User();
//            user.setId(i);
//            user.setUserName("user" + (i - 1));
//            user.setBirthday(LocalDate.now());
//            user.setAuthorization(UserAuthorization.USER);
//
//            userService.save(user);
//        }
    }

    // 4) 과제
    public void createDummyAssignments() {

        Assignment a1 = Assignment.builder()
                .title("개인 과제 1")
                .content("개인 과제 1 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.INDIVIDUAL)
                .isClose(false)
                .dueDate(LocalDate.now().plusDays(3))
                .build();

        Assignment a2 = Assignment.builder()
                .title("팀 과제 1")
                .content("팀 과제 1 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.TEAM)
                .isClose(false)
                .presentationOrderTeamId("1,2")
                .dueDate(LocalDate.now().plusDays(7))
                .build();

        assignmentService.save(a1);
        assignmentService.save(a2);
    }

    // 5) 제출물
    public void createDummySubmissions() {

        // 개인 과제 (assignmentId = 1)
        for (long userId = 2; userId <= 5; userId++) {

            boolean submitted = (userId == 2L || userId == 4L);

            Submission.Builder builder = new Submission.Builder()
                    .assignmentId(1L)
                    .submitterUserId(userId)
                    .content("개인 과제 1 제출 - user" + (userId - 1));

            if (submitted) {
                builder.submittedAt(LocalDate.now().minusDays(userId));
            }

            submissionService.save(builder.build());
        }

        // 팀과제 (assignmentId = 2)
        Submission team1 = new Submission.Builder()
                .assignmentId(2L)
                .submitterUserId(2L)
                .content("팀1 팀 과제 제출본")
                .submittedAt(LocalDate.now())
                .build();

        Submission team2 = new Submission.Builder()
                .assignmentId(2L)
                .submitterUserId(4L)
                .content("팀2 팀 과제 (미제출)")
                .build();

        submissionService.save(team1);
        submissionService.save(team2);
    }
}
