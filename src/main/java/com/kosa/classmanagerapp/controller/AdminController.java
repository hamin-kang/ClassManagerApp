package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.attendance.Attendance;
import com.kosa.classmanagerapp.model.attendance.Status;
import com.kosa.classmanagerapp.service.AttendanceService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.List;

public class AdminController {
//    public TextArea adminTextArea;
//    public VBox middlebox;
//    public TextField title_admin;
//    public TextField content_admin;
//    public CheckBox assignmentType_team;
//    public CheckBox assignmentType_person;
//    public DatePicker dueDate;




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
    private ComboBox<String> taskComboBox; //과제 드롭다운
    private void TaskList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
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

//    @FXML
//    public void initialize() { // 드롭다운
//
//        if (TaskTable != null) {
//            // TableView 폭에 맞춰 컬럼 폭 자동 조절
//            TaskTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        }
//        if (attendanceTable != null) {
//            attendanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//        }
//        //fxml 로딩 후 안전하게 실행
//        Platform.runLater(() -> loadTeamList());
//        Platform.runLater(() -> TaskList());
//    }
//-----------------------------------------------




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
    }

    private void loadData() {
        List<Attendance> list = attendanceService.getAttendanceList();
        ObservableList<Attendance> obsList = FXCollections.observableArrayList(list);
        tableView.setItems(obsList);
    }



    @FXML
    protected void teamCreateButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/team-create.fxml");

    }

    @FXML
    protected void taskCreateButtonClick(ActionEvent event) throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/project-create.fxml");



    }
    @FXML
    protected void turnOrderButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/turn-order.fxml");

    }






}
