package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.service.SubmissionService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.time.LocalDate;
import java.util.List;

public class UserController {

    SubmissionService submissionService = new SubmissionService();
    @FXML private TableView<Submission> privateTaskTable;
    @FXML private TableColumn<Submission, Boolean> colSubmitted;
    @FXML private TableColumn<Submission, String> colTitle;
    @FXML private TableColumn<Submission, LocalDate> colDate;

    @FXML
    public void initialize() throws Exception {
        //해당 유저의 개인과제 제출 리스트를 가져온다
        User user = SessionService.getUser();
        if (user != null) {
            System.out.println("Current User " + user.getUserName());
        }
        List<Submission> userSubmissions = submissionService.findByUserId(user.getId());
        if(!userSubmissions.isEmpty()){
            setPrivateTable(userSubmissions);
        }
        //해당 유저의 팀과제 제출 리스트를 가져온다


    }
    @FXML
    protected void logoutClick() throws Exception {
        SessionService.clear();

        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void submitPrivate() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void submitTeam() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void setPrivateTable(List<Submission> userSubmissions) {
        // 제출 여부
        colSubmitted.setCellValueFactory(cell ->
                new SimpleObjectProperty<>(cell.getValue().getIsSubmitted())
        );

        colSubmitted.setCellFactory(tc -> new CheckBoxTableCell<>(index -> {
            Submission item = privateTaskTable.getItems().get(index);

            BooleanProperty bp = new SimpleBooleanProperty(item.getIsSubmitted());

            // UI 체크박스를 클릭하면 모델에도 반영
            bp.addListener((obs, oldVal, newVal) -> {
                item.setIsSubmitted(newVal);
                System.out.println("Click new " + newVal + " old " + oldVal);

            });

            return bp;
        }));

        // 과제 이름
        colTitle.setCellValueFactory(cell ->
                new SimpleStringProperty("과제 " + cell.getValue().getAssignmentId())
        );

        // 제출 날짜(LocalDate)
        colDate.setCellValueFactory(cell ->
                new SimpleObjectProperty<>(cell.getValue().getSubmittedAt())
        );

        // TableView 채우기
        privateTaskTable.setItems(FXCollections.observableArrayList(userSubmissions));
    }

}
