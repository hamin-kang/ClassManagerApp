package com.kosa.classmanagerapp.global.initData;

import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.*;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime ;
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
//        createDummyUsers();
        createDummyTeams();
        updateDummyUsers();

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

        int result1 =  teamService.save(team1);
        int result2 = teamService.save(team2);
        System.out.println("InitDataDB::createDummyTeams " + result1);
        System.out.println("InitDataDB::createDummyTeams " + result2);
    }

    // 3) 유저
    public void createDummyUsers() {
//
//        // admin
//        User admin = new User();
//        admin.setId(1L);
//        admin.setUserName("admin");
//        admin.setBirthday(LocalDateTime .now());
//        admin.setAuthorization(UserAuthorization.ADMIN);
//        userService.save(admin);
//
//        // 일반 유저 4명
//        for (long i = 2; i <= 5; i++) {
//            User user = new User();
//            user.setId(i);
//            user.setUserName("user" + (i - 1));
//            user.setBirthday(LocalDateTime .now());
//            user.setAuthorization(UserAuthorization.USER);
//
//            userService.save(user);
//        }
    }
    //user 팀 할당
    public void updateDummyUsers() {

        for (long i = 2; i <= 5; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("user" + (i - 1));
            user.setBirthday(LocalDate.now());
            user.setAuthorization(UserAuthorization.USER);

            // 팀 배정 로직
            if (i == 2 || i == 3) {
                user.setTeamId(1L);
            } else {
                user.setTeamId(2L);
            }

            int result = userService.updateTeam(user);
            System.out.println("InitDataDB::updateDummyUser " + i + " " +result);
        }
    }
    // 4) 과제
    public void createDummyAssignments() {

        Assignment a1 = Assignment.builder()
                .title("개인 과제 1")
                .content("개인 과제 1 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.INDIVIDUAL)
                .isClose(true)
                .dueDate(LocalDateTime .now().plusDays(3))
                .build();
        Assignment a2 = Assignment.builder()
                .title("개인 과제 2")
                .content("개인 과제 2 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.INDIVIDUAL)
                .isClose(false)
                .dueDate(LocalDateTime .now().plusDays(3))
                .build();
        Assignment a3 = Assignment.builder()
                .title("팀 과제 1")
                .content("팀 과제 1 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.TEAM)
                .isClose(true)
                .presentationOrderTeamId("1,2")
                .dueDate(LocalDateTime .now().plusDays(7))
                .build();
        Assignment a4 = Assignment.builder()
                .title("팀 과제 2")
                .content("팀 과제 2 내용")
                .creatorId(1L)
                .assignmentType(AssignmentType.TEAM)
                .isClose(false)
                .presentationOrderTeamId("1,2")
                .dueDate(LocalDateTime .now().plusDays(7))
                .build();

        assignmentService.save(a1);
        assignmentService.save(a2);
        assignmentService.save(a3);
        assignmentService.save(a4);

    }

    // 5) 제출물
    public void createDummySubmissions() {

        // 개인 과제 (assignmentId = 1)
        Submission user1 = new Submission.Builder()
                .assignmentId(1L)
                .submitterUserId(2L)
                .content("개인 과제 1 제출 - user1" )
                .submittedAt(LocalDateTime .now())
                .build();

        Submission user3 = new Submission.Builder()
                .assignmentId(1L)
                .submitterUserId(4L)
                .content("개인 과제 1 제출 - user3" )
                .submittedAt(LocalDateTime .now())
                .build();

        submissionService.save(user1);
        submissionService.save(user3);


        // 팀과제 (assignmentId = 3)
        Submission team1 = new Submission.Builder()
                .assignmentId(3L)
                .submitterUserId(2L)
                .content("팀1 팀 과제 제출본")
                .teamId(1L)
                .submittedAt(LocalDateTime .now())
                .build();

        submissionService.save(team1);
    }
}
