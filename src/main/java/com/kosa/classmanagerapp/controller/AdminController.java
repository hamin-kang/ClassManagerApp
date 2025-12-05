package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.attendance.Attendance;
import com.kosa.classmanagerapp.service.AttendanceService;
import com.kosa.classmanagerapp.service.submission.SubmissionService;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
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
//    @FXML
//    private TableColumn<Attendance, String> colSessionDate;
//    @FXML
//    private TableColumn<Attendance, String> colStatus;

    private final AttendanceService attendanceService = new AttendanceService();


@FXML
private TableView<Attendance> attendanceRankingTable;

    @FXML
    private TableColumn<Attendance, Integer> colRanking;
    @FXML
    private TableColumn<Attendance, String> colFullName;
    @FXML
    private TableColumn<Attendance, Integer> colPresentCount;

// ==================================================
    @FXML
    private TableView<Submission> submissionSummaryTable;
    @FXML
    private TableColumn<Submission, String> colSubmitFullName;
    @FXML
    private TableColumn<Submission, Integer> colSubmissionCount;

    private final SubmissionService submissionService = new SubmissionService();



    //출석 테이블 로드
    @FXML
    public void initialize() {
        if (colId != null) {
//            colId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
//            colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
//            colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
//            colSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
//            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//
//
//            // LocalDate -> String
//            colSessionDate.setCellValueFactory(cellData ->
//                    new SimpleStringProperty(cellData.getValue().getSessionDate().toString())
//            );
//
//            // Enum -> String
//            colStatus.setCellValueFactory(cellData ->
//                    new SimpleStringProperty(cellData.getValue().getStatus().name())
//            );

            loadData();
        }
//  ====================================================================


        // 출석 랭킹
        if (colRanking != null) {
            colRanking.setCellValueFactory(new PropertyValueFactory<>("ranking"));
            colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            colPresentCount.setCellValueFactory(new PropertyValueFactory<>("presentCount"));

            loadRankingData(); // 랭킹 데이터 로드
        }

        Platform.runLater(this::TaskList);
//    -=== 과제 랭킹

        if (colSubmitFullName != null) {
//            colSubmitFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
//            colSubmissionCount.setCellValueFactory(new PropertyValueFactory<>("submissionCount"));
            colSubmitFullName.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getFullName())
            );

            colSubmissionCount.setCellValueFactory(cellData ->
                    new SimpleIntegerProperty(cellData.getValue().getSubmissionCount()).asObject()
            );

            loadSubmissionSummary();
        }

//    ====================================================================
        // FXML 로딩 후 안전하게 실행
        Platform.runLater(() -> {

            TaskList();
        });
        
    }
//   ====================================================================



    private void loadRankingData() {
        List<Attendance> rankingList = attendanceService.selectAttendanceRanking(); // 랭킹 SQL 호출
        ObservableList<Attendance> obsList = FXCollections.observableArrayList(rankingList);
        attendanceRankingTable.setItems(obsList);
    }


//    --------------------------------------------------------------------------------------------------

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

    @FXML
    private void loadSubmissionSummary() {
        List<Submission> list = submissionService.getSubmissionSummary();
        ObservableList<Submission> obsList = FXCollections.observableArrayList(list);
        submissionSummaryTable.setItems(obsList);
    }

}
