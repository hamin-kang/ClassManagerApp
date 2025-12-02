package com.kosa.classmanagerapp.controller.Admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class teamCreateConroller {

    @FXML
    protected void adminButtonClick(ActionEvent event) throws Exception {
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
}
