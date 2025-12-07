package com.kosa.classmanagerapp.controller.admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.model.entity.UserAuthorization;
import com.kosa.classmanagerapp.service.TeamService;
import com.kosa.classmanagerapp.service.auth.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class TeamCreateController {

    @FXML private VBox memberListContainer;
    @FXML private ListView<String> selectedListView;
    @FXML private ComboBox<String> teamComboBox;

    private final UserService userService = new UserService();
    private final TeamService teamService = new TeamService();

    @FXML
    public void initialize() {
        loadTeamList();
        loadMembers();
    }

    private void loadTeamList() {
        teamComboBox.getItems().addAll("1팀", "2팀", "3팀", "4팀", "5팀");
        teamComboBox.getSelectionModel().selectFirst();
    }

    private void loadMembers() {
        try {
            memberListContainer.getChildren().clear();
            List<User> users = userService.findAllUser();

            for (User user : users) {
                if(user.getAuthorization() == UserAuthorization.ADMIN) continue;

                HBox row = new HBox(10);
                Label nameLabel = new Label(user.getFullName() + " (" + user.getUserName() + ")" );
                Button selectButton = new Button("선택");

                selectButton.setOnAction(e -> {
                    String display = user.getId() + " - " + user.getFullName();
                    if (!selectedListView.getItems().contains(display)) {
                        selectedListView.getItems().add(display);
                    }
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
        String team_name = teamComboBox.getSelectionModel().getSelectedItem();

        if (team_name == null || team_name.isEmpty()) {
            showAlert("오류", "팀을 먼저 선택해주세요.");
            return;
        }

        if (selectedListView.getItems().isEmpty()) {
            showAlert("오류", "선택된 멤버가 없습니다.");
            return;
        }

        try {
            // 1. 팀 생성 또는 업데이트
            // (DB에 없으면 만들고, 있으면 업데이트. project_id는 Mapper에서 1로 고정됨)
            Team team = new Team();
            // 주의: 여기서 setIdInt를 해도 insert 시에는 무시되고 Auto Increment 될 수 있습니다.
            // 하지만 update 시에는 식별자가 필요하므로 일단 파싱한 ID를 넣습니다.
            int parsedId = parseTeamId(team_name);
            team.setIdInt(parsedId);
            team.setTeamName(team_name);

            teamService.saveOrUpdateTeamMember(team);

            // 2. [핵심] DB에 실제 저장된 ID 확인
            // MyBatis의 useGeneratedKeys 덕분에 insert 후 team 객체에 ID가 채워집니다.
            // 만약 update였다면 parsedId가 그대로 유지됩니다.
            long realTeamId = team.getId() != null ? team.getId() : parsedId;

            // 3. 유저 정보 업데이트
            List<Long> selectedUserIds = new ArrayList<>();
            for (String member : selectedListView.getItems()) {
                String[] parts = member.split(" - ");
                if (parts.length > 0) selectedUserIds.add(Long.parseLong(parts[0]));
            }

            int updateCount = 0;
            for (Long userId : selectedUserIds) {
                // 아까 에러났던 부분 해결: DB에 존재하는 realTeamId를 넣습니다.
                userService.updateUserTeam(userId, (int)realTeamId);
                updateCount++;
            }

            showAlert("성공", team_name + " (ID: " + realTeamId + ") 구성 완료! (" + updateCount + "명)");
            selectedListView.getItems().clear();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("에러 발생", "DB 오류: " + e.getMessage());
        }
    }

    private int parseTeamId(String teamname){
        return Integer.parseInt(teamname.replace("팀", ""));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void adminButtonClick(ActionEvent event) throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");
    }
}