package com.kosa.classmanagerapp.controller.admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.service.TeamService;
import com.kosa.classmanagerapp.service.auth.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TeamCreateController {

    @FXML private VBox memberListContainer;
    @FXML private ListView<String> selectedListView;
    @FXML private ComboBox<String> teamComboBox;

    private final UserService userService = new UserService();



    @FXML
    protected void adminButtonClick(ActionEvent event) throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }


    @FXML
    public void initialize() { // 드롭다운
        loadTeamList();
        loadMembers();
    }



    private void loadTeamList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
        teamComboBox.getItems().addAll(
                "1팀", "2팀", "3팀", "4팀", "5팀"
        );

        // 기본 선택값
        teamComboBox.getSelectionModel().selectFirst();
    }

    // 팀 로드

    private void loadMembers() {
        try {
            // DB에서 실제 사용자 목록 가져오기
            List<User> users = userService.findAllUser();
            System.out.println("users !!!!!!! =" + users); // 디버깅용

            for (User user : users) {
                HBox row = new HBox(10);

//                System.out.println("userName = " + user.getUserName());

                Label nameLabel = new Label(user.getFullName() + " (" + user.getUserName() + ")" );
                Button selectButton = new Button("선택");

                selectButton.setOnAction(e -> {
                    String display = user.getId() + " - " + user.getFullName();
                    if (!selectedListView.getItems().contains(display)) {
                        selectedListView.getItems().add(display);
                    }
                    //  userName도 바로 가져오기
                    String userName = user.getUserName();
                    System.out.println("선택된 멤버 userName: " + userName);

                });

                row.getChildren().addAll(nameLabel, selectButton);
                memberListContainer.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void createTeamButtonClick(ActionEvent event) {

        // 1. 선택된 팀명 가져오기
        String team_name = teamComboBox.getSelectionModel().getSelectedItem();
        int teamId = parseTeamId(team_name);
        if (team_name != null) {


          //  int teamId = parseTeamId(team_name); // ← 팀명에서 숫자 추출
            System.out.println("선택된 팀: " + team_name);
            System.out.println("team_id 값 = " + teamId);
        } else {
            System.out.println("팀이 선택되지 않았습니다.");
            return;
        }

        // 2. 선택된 멤버 정보 출력
        if (selectedListView.getItems().isEmpty()) {
            System.out.println("선택된 멤버가 없습니다.");
            return;
        }

        System.out.println("선택된 멤버:");
        for (String member : selectedListView.getItems()) {

// --------------user_name 모두 추출--------------------------
            String[] parts = member.split(" - ");
            String userfullName = parts[1];
            System.out.println(" user_name = " + userfullName);
        }
        //  }

        //--------------------

        List<Long> selectedUserIds = new ArrayList<>();
        for (String member : selectedListView.getItems()) {
            String[] parts = member.split(" - ");
            long userId = Long.parseLong(parts[0]);
            selectedUserIds.add(userId);
            System.out.println("선택된 userId = " + userId);
        }


        // 3. TeamService 준비
        TeamService teamService = new TeamService();

        // 4. 팀 업데이트 (각 유저의 teamId를 선택된 팀으로 업데이트)
//        int updateCount = 0;
//        for (Long userId : selectedUserIds) {
//            try {
//                Team team = new Team();
//                team.setIdInt(teamId);      // 팀 ID
//                team.setUserId(userId);
//                team.setTeamName(team_name);// User ID
//                updateCount += teamService.updateTeamMember(team);
//                System.out.println("업데이트 대상 teamId = " + teamId +" 유저아이디 = " + userId  +"팀이름" + team_name);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        //5. 팀id 존재여부에 따라 insert or update
        int updateCount = 0;

        for (Long userId : selectedUserIds) {
            try {
                Team team = new Team();
                team.setIdInt(teamId);      // 팀 ID (없으면 0)
                team.setUserId(userId);
                team.setTeamName(team_name);

                updateCount += userService.updateUserTeam(userId, teamId);

                updateCount += teamService.saveOrUpdateTeamMember(team);

                System.out.println("업데이트 또는 삽입 팀ID = " + teamId
                        + " 유저ID = " + userId
                        + " 팀이름 = " + team_name);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(updateCount + "건 처리 완료");


        System.out.println(updateCount + " 명의 팀 정보가 업데이트 되었습니다.");
    }
    //    //----------------팀 id -----------------
    private int parseTeamId (String teamname){
        return Integer.parseInt(teamname.replace("팀", "")); // "1팀" → 1
    }




    }






















