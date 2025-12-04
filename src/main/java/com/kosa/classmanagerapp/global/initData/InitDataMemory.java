package com.kosa.classmanagerapp.global.initData;

import com.kosa.classmanagerapp.model.*;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.model.entity.UserAuthorization;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InitDataMemory {

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
                .build();
        Team team2 = Team.builder()
                .teamName("2팀")
                .projectId(1L)
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
                        .dueDate(LocalDateTime.now().plusDays(3))
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
                        .dueDate(LocalDateTime.now().plusDays(7))
                        .build()
        );

        return list;
    }
    public static List<Submission> createDummySubmissions() {
        List<Submission> list = new ArrayList<>();

        Long assignmentId1 = 1L; // 개인 과제 ID

        for (long userId = 2L; userId <= 5L; userId++) {
            Submission submission = new Submission.Builder()
                    .assignmentId(assignmentId1)
                    .submitterUserId(userId)
                    .submittedAt(LocalDateTime.now())
                    .build();

            list.add(submission);
        }

        Long assignmentId2 = 2L; // 개인 과제 ID

        for (long userId = 2L; userId <= 5L; userId++) {
            Submission submission = new Submission.Builder()
                    .assignmentId(assignmentId2)
                    .submitterUserId(userId)
                    .submittedAt(LocalDateTime.now())
                    .build();

            list.add(submission);        }

        Long assignmentId3 = 3L; // 팀 과제 ID

        for (long userId = 2L; userId <= 5L; userId++) {

            Long teamId = (userId <= 3L) ? 1L : 2L;

            Submission submission = new Submission.Builder()
                    .assignmentId(assignmentId3)
                    .submitterUserId(userId)
                    .teamId(teamId)
                    .submittedAt(LocalDateTime.now())
                    .build();

            list.add(submission);
        }

        return list;
    }

}
