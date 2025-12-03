package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        main.loadView("view/admin/turn-order.fxml");
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
        if (teamComboBox == null) return;
        teamComboBox.getItems().addAll(
                "1팀", "2팀", "3팀", "4팀", "5팀"
        );

        // 기본 선택값
        teamComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private ComboBox<String> taskComboBox; //과제 드롭다운
    private void TaskList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
        if (taskComboBox == null) return;
        taskComboBox.getItems().addAll(
                "알고리즘", "미니과제", "발표", "UML", "final프로젝트"
        );

        // 기본 선택값
        taskComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private TableView<?> TaskTable;

    @FXML
    private TableView<?> attendanceTable;

    @FXML
    public void initialize() { // 드롭다운

        if (TaskTable != null) {
            // TableView 폭에 맞춰 컬럼 폭 자동 조절
            TaskTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
        if (attendanceTable != null) {
            attendanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        }

        // FXML 로딩 후 안전하게 실행
        Platform.runLater(() -> {
            loadTeamList();
            TaskList();
        });
    }

}
