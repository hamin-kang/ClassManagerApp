package com.kosa.classmanagerapp.global;

import com.kosa.classmanagerapp.model.*;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InitData {

    public static List<Project> createDummyProjects() {
        List<Project> list = new ArrayList<>();
        Project project = Project.builder()
                .projectName("1차프로젝트")
                .build();
        list.add(project);
        return list;
    }
    public static List<Team> createDummyTeams() {
        List<Team> list = new ArrayList<>();
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
        list.add(team1);
        list.add(team2);
        return list;
    }
    public static List<User> createDummyUsers() {
        List<User> list =  new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("admin");
        user1.setBirthday(LocalDate.now());
        user1.setAuthorization(UserAuthorization.ADMIN);
        list.add(user1);
        // 유저 4명 생성 ,Team1-(2,3), Team2-(4,5)
        for (long i = 2; i <= 5; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("user" + (i - 1)); // user1, user2, user3, user4
            user.setBirthday(LocalDate.now());
            user.setAuthorization(UserAuthorization.USER);

            list.add(user);
        }
        return list;
    }
    public static List<Assignment> createDummyAssignments() {
        List<Assignment> list = new ArrayList<>();
        list.add(
                Assignment.builder()
                        .title("개인 과제 1")
                        .content("개인 과제 1 내용")
                        .creatorId(1L) // admin
                        .assignmentType(AssignmentType.INDIVIDUAL) // enum 값에 맞게
                        .isClose(false)
                        .dueDate(LocalDate.now().plusDays(3))
                        .build()
        );

        list.add(
                Assignment.builder()
                        .title("팀 과제 1")
                        .content("팀 과제 1 내용")
                        .creatorId(1L)
                        .assignmentType(AssignmentType.TEAM)
                        .isClose(false)
                        .presentationOrderTeamId("1,2")
                        .dueDate(LocalDate.now().plusDays(7))
                        .build()
        );

        return list;
    }
    public static List<Submission> createDummySubmissions() {
        List<Submission> list = new ArrayList<>();

        // 1) 개인 과제 (assignmentId = 1)
        // 유저 2, 3, 4, 5 중 2, 4만 제출
        for (long userId = 2; userId <= 5; userId++) {
            boolean submitted = (userId == 2L || userId == 4L);

            Submission.Builder builder = new Submission.Builder()
                    .assignmentId(1L)                      // 개인 과제 1
                    .submitterUserId(userId)
                    .content("개인 과제 1 제출 - user" + (userId - 1));

            if (submitted) {
                builder.submittedAt(LocalDate.now().minusDays(userId)); // 제출한 사람만 날짜 세팅
            }
            // 미제출이면 submittedAt 안 넣어서 null 유지

            list.add(builder.build());
        }

        // 2) 팀 과제 (assignmentId = 2)
        // 팀1 리더(2번)는 제출함
        Submission team1Submission = new Submission.Builder()
                .assignmentId(2L)
                .submitterUserId(2L) // 팀1 리더
                .content("팀1 팀 과제 제출본")
                .submittedAt(LocalDate.now())
                .build();
        list.add(team1Submission);

        // 팀2 리더(4번)는 아직 미제출 → submittedAt 설정 안 함
        Submission team2Submission = new Submission.Builder()
                .assignmentId(2L)
                .submitterUserId(4L) // 팀2 리더
                .content("팀2 팀 과제 (미제출 상태)")
                // .submittedAt(...) 안 넣어서 null = 미제출
                .build();
        list.add(team2Submission);

        return list;
    }

}
