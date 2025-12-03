package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.attendance.Attendance;
import com.kosa.classmanagerapp.service.AttendanceService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
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
        main.loadView("view/admin/turn-order.fxml");
    }

    @FXML
    protected void adminButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

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
    private TableView<Attendance> tableView;
    @FXML
    private TableColumn<Attendance, Integer> colId;
    @FXML
    private TableColumn<Attendance, Integer> colUserId;
    @FXML
    private TableColumn<Attendance, String> colUserName;
    @FXML
    private TableColumn<Attendance, String> colSessionDate;
    @FXML
    private TableColumn<Attendance, String> colStatus;

    private final AttendanceService attendanceService = new AttendanceService();


    //출석 테이블 로드
    @FXML
    public void initialize() {
        if (colId != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
            colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
            colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
            colSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


            // LocalDate -> String
            colSessionDate.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getSessionDate().toString())
            );

            // Enum -> String
            colStatus.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getStatus().name())
            );

            loadData();
        }
        // FXML 로딩 후 안전하게 실행
        Platform.runLater(() -> {

            TaskList();
        });
        
    }

    private void loadData() {
        List<Attendance> list = attendanceService.getAttendanceList();
        ObservableList<Attendance> obsList = FXCollections.observableArrayList(list);
        tableView.setItems(obsList);
    }

    @FXML
    protected void taskCreateButtonClick(ActionEvent event) throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/project-create.fxml");

    }

}
