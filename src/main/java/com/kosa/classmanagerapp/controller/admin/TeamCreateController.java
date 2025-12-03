package com.kosa.classmanagerapp.controller.admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.service.TeamService;
import com.kosa.classmanagerapp.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class TeamCreateController {

    @FXML private VBox memberListContainer;
    @FXML private ListView<String> selectedListView;
    @FXML
    private ComboBox<String> teamComboBox;

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
            System.out.println("users =" + users); // 디버깅용

            for (User user : users) {
                HBox row = new HBox(10);

                Label nameLabel = new Label(user.getFullName() + " (" + user.getUserName() + ")");
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
        // 1. 선택된 팀명 가져오기
        String selectedTeam = teamComboBox.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            System.out.println("선택된 팀: " + selectedTeam);
        } else {
            System.out.println("팀이 선택되지 않았습니다.");
        }

        // 2. 선택된 멤버 가져오기
        if (!selectedListView.getItems().isEmpty()) {
            System.out.println("선택된 멤버:");
            selectedListView.getItems().forEach(member -> System.out.println("- " + member));
        } else {
            System.out.println("선택된 멤버가 없습니다.");
        }
    }

}






