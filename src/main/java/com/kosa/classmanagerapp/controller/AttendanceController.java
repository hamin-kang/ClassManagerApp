package com.kosa.classmanagerapp.controller;



import com.kosa.classmanagerapp.model.attendance.Attendance;
import com.kosa.classmanagerapp.service.AttendanceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AttendanceController {

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

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadData();
    }

    private void loadData() {
        List<Attendance> list = attendanceService.getAttendanceList();
        ObservableList<Attendance> obsList = FXCollections.observableArrayList(list);
        tableView.setItems(obsList);
    }
}
