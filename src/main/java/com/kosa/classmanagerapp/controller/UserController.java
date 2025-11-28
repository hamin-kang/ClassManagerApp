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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {

    SubmissionService submissionService = new SubmissionService();
    //체크박스 임시 저장용 Map, 제출 버튼 클릭 시 실제 값 반영
    private final Map<Long, Boolean> pendingSubmittedMap = new HashMap<>();
    @FXML private TableView<Submission> privateTaskTable;
    @FXML private TableColumn<Submission, Boolean> colSubmitted;
    @FXML private TableColumn<Submission, String> colTitle;
    @FXML private TableColumn<Submission, LocalDate> colDate;

    // 테이블 데이터 전용 리스트
    private final javafx.collections.ObservableList<Submission> privateTaskItems =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() throws Exception {
        setupPrivateTableColumns();
        loadPrivateTableData();

    }
    @FXML
    public void refreshPrivate() throws Exception {
        loadPrivateTableData();
    }
    @FXML
    public void refreshTeam() throws Exception {
        loadPrivateTableData();
    }
    @FXML
    protected void logoutClick() throws Exception {
        SessionService.clear();

        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void submitPrivate() throws Exception {
        // 테이블에 있는 모든 행 기준으로
        for (Submission s : privateTaskItems) {
            Boolean newVal = pendingSubmittedMap.get(s.getAssignmentId());
            if (newVal != null && newVal != s.isSubmitted()) {
                // 실제 엔티티에 반영
                s.setSubmitted(newVal);
                // DB 반영 (메서드 이름은 프로젝트에 맞게 수정)
//                submissionService.updateSubmittedStatus(s.getId(), newVal);
            }
        }

        // 임시 변경사항 비우기
        pendingSubmittedMap.clear();

        // 필요하면 새로 로딩
        loadPrivateTableData();

    }

    @FXML
    protected void submitTeam() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void setupPrivateTableColumns() {
        privateTaskTable.setEditable(true);
        colSubmitted.setEditable(true);

        colSubmitted.setCellValueFactory(cellData -> {
            Submission s = cellData.getValue();

            // 이미 바꾼 값이 있으면 그 값, 없으면 원래 DB 값 사용
            boolean initialValue = pendingSubmittedMap.getOrDefault(
                    s.getAssignmentId(),
                    s.isSubmitted()
            );

            BooleanProperty prop = new SimpleBooleanProperty(initialValue);

            // 체크박스 클릭해서 값 바뀔 때마다 임시 Map 에만 반영
            prop.addListener((obs, oldVal, newVal) -> {
                pendingSubmittedMap.put(s.getAssignmentId(), newVal);
            });

            return prop;
        });
        colSubmitted.setCellFactory(CheckBoxTableCell.forTableColumn(colSubmitted));

        colTitle.setCellValueFactory(cell ->
                new SimpleStringProperty("과제 " + cell.getValue().getAssignmentId())
        );

        colDate.setCellValueFactory(cell ->
                new SimpleObjectProperty<>(cell.getValue().getSubmittedAt())
        );

        // 여기서 한 번만 items 바인딩
        privateTaskTable.setItems(privateTaskItems);
    }

    private void loadPrivateTableData() {
        System.out.println("***Load***");
        pendingSubmittedMap.clear();
        User user = SessionService.getUser();
        if (user == null) {
            privateTaskItems.clear();
            return;
        }

        List<Submission> userSubmissions =
                submissionService.findByUserId(user.getId());
        for (int i = 0; i < userSubmissions.size(); i++) {
            System.out.println(
                    userSubmissions.get(i).getAssignmentId() + " " +
                            userSubmissions.get(i).isSubmitted()
            );
        }

        // 기존 데이터 싹 지우고 새로 채움 (clear + addAll과 같음)
        privateTaskItems.setAll(userSubmissions);
    }
}
