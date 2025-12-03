package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.Notice;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.model.dto.SubmissionStatusResponse;
import com.kosa.classmanagerapp.service.NoticeService;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.service.SubmissionService;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.time.LocalDate;
import java.util.List;

public class UserController {

    SubmissionService submissionService = new SubmissionService();
    NoticeService NoticeService = new NoticeService();
    @FXML private ListView<Notice> noticeListView;
    @FXML private TableView<SubmissionStatusResponse> individualTaskTable;
    @FXML private TableView<SubmissionStatusResponse> teamTaskTable;

    @FXML private TableColumn<SubmissionStatusResponse, Boolean> colIndividualSubmitted;
    @FXML private TableColumn<SubmissionStatusResponse, String> colIndividualTitle;
    @FXML private TableColumn<SubmissionStatusResponse, LocalDate> colIndividualDate;

    @FXML private TableColumn<SubmissionStatusResponse, Boolean> colTeamSubmitted;
    @FXML private TableColumn<SubmissionStatusResponse, String> colTeamTitle;
    @FXML private TableColumn<SubmissionStatusResponse, LocalDate> colTeamDate;

    // 테이블 데이터 전용 리스트
    private final javafx.collections.ObservableList<SubmissionStatusResponse> individualTaskItems =
            FXCollections.observableArrayList();
    private final javafx.collections.ObservableList<SubmissionStatusResponse> teamTaskItems =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() throws Exception {
        loadNoticeList();
        setupIndividualTableColumns();
        setupTeamTableColumns();

        loadIndividualTableData();
        loadTeamTableData();

        individualTaskTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                SubmissionStatusResponse selected = individualTaskTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    openDetailPage(selected);
                }
            }
        });

        teamTaskTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                SubmissionStatusResponse selected = teamTaskTable.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    openDetailPage(selected);
                }
            }
        });

    }
    @FXML
    public void refreshIndividual() {
        loadIndividualTableData();
    }
    @FXML
    public void refreshTeam() {
        loadTeamTableData();
    }
    @FXML
    protected void logoutClick() throws Exception {
        SessionService.clear();

        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void setupIndividualTableColumns() {

        colIndividualSubmitted.setCellValueFactory(cellData -> {
            SubmissionStatusResponse s = cellData.getValue();
            return new SimpleBooleanProperty(s.isSubmitted());
        });
        colIndividualSubmitted.setCellFactory(CheckBoxTableCell.forTableColumn(colIndividualSubmitted));

        colIndividualTitle.setCellValueFactory(cell ->
                new SimpleStringProperty("과제 " + cell.getValue().assignmentName())
        );

        colIndividualDate.setCellValueFactory(cell -> {
            LocalDate dt = cell.getValue().dueDate();
            return new SimpleObjectProperty<>(dt);
        });
        applyIndividualColumnResizePolicy();

        individualTaskTable.setItems(individualTaskItems);
    }

    //표 사이즈 조정
    private void applyIndividualColumnResizePolicy() {
        individualTaskTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        colIndividualSubmitted.prefWidthProperty().bind(
                individualTaskTable.widthProperty().multiply(0.20) // 20%
        );
        colIndividualTitle.prefWidthProperty().bind(
                individualTaskTable.widthProperty().multiply(0.50) // 50%
        );
        colIndividualDate.prefWidthProperty().bind(
                individualTaskTable.widthProperty().multiply(0.30) // 30%
        );
    }
    private void applyTeamColumnResizePolicy() {
        teamTaskTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        colTeamSubmitted.prefWidthProperty().bind(
                teamTaskTable.widthProperty().multiply(0.20)
        );
        colTeamTitle.prefWidthProperty().bind(
                teamTaskTable.widthProperty().multiply(0.50)
        );
        colTeamDate.prefWidthProperty().bind(
                teamTaskTable.widthProperty().multiply(0.30)
        );
    }
    @FXML
    protected void setupTeamTableColumns() {
        colTeamSubmitted.setCellValueFactory(cellData -> {
            SubmissionStatusResponse s = cellData.getValue();
            return new SimpleBooleanProperty(s.isSubmitted());
        });
        colTeamSubmitted.setCellFactory(CheckBoxTableCell.forTableColumn(colTeamSubmitted));

        colTeamTitle.setCellValueFactory(cell ->
                new SimpleStringProperty("과제 " + cell.getValue().assignmentId())
        );

        colTeamDate.setCellValueFactory(cell -> {
            LocalDate dt = cell.getValue().dueDate();
            return new SimpleObjectProperty<>(dt);
        });
        applyTeamColumnResizePolicy();

        teamTaskTable.setItems(teamTaskItems);
    }
    private void loadIndividualTableData() {
        System.out.println("***Load Individual***");
        User user = SessionService.getUser();
        if (user == null) {
            individualTaskItems.clear();
            return;
        }

        List<SubmissionStatusResponse> userSubmissions =
                submissionService.findByUserIdIndividualSubmissions(user.getId());

        individualTaskItems.setAll(userSubmissions);
    }

    private void loadTeamTableData() {
        User user = SessionService.getUser();
        if (user == null) {
            teamTaskItems.clear();
            return;
        }

        List<SubmissionStatusResponse> userSubmissions =
                submissionService.findByUserIdTeamSubmissions(user.getId());

        teamTaskItems.setAll(userSubmissions);
    }
    private void openDetailPage(SubmissionStatusResponse submission) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/kosa/classmanagerapp/view/user/submission-detail-view.fxml")
            );
            Parent root = loader.load();

            MainController main = MainApplication.getMainController();
            main.setContent(root);

            SubmissionDetailController controller = loader.getController();
            controller.setSubmissionDetail(submission);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadNoticeList() {
        List<Notice> notices = NoticeService.findAll();
        noticeListView.getItems().setAll(notices);
    }}
