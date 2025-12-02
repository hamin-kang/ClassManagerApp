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
