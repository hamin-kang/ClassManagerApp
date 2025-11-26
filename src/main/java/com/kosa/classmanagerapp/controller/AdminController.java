package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import javafx.application.Platform;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class AdminController {
    public TextArea adminTextArea;

    @FXML
    protected void teamCreateButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();

         main.loadView("view/admin/team-create.fxml");

    }

    @FXML
    protected void taskCreateButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/task-create.fxml");

    }

    @FXML
    protected void turnOrderButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/task-create.fxml");

    }

    @FXML
    protected void adminButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }

    @FXML
    private ComboBox<String> teamComboBox;


    private void loadTeamList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
        teamComboBox.getItems().addAll(
                "1팀", "2팀", "3팀", "4팀", "5팀"
        );

        // 기본 선택값
        teamComboBox.getSelectionModel().selectFirst();
    }


    @FXML
    public void initialize() { // 드롭다운
        //fxml 로딩 후 안전하게 실행
        Platform.runLater(() -> loadTeamList());
    }


}
