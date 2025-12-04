package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.Notice;
import com.kosa.classmanagerapp.model.dto.auth.ChangePasswordRequest;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.model.dto.submission.SubmissionStatusResponse;
import com.kosa.classmanagerapp.service.NoticeService;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.service.submission.SubmissionService;

import com.kosa.classmanagerapp.service.auth.UserService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserController {

    SubmissionService submissionService = new SubmissionService();
    NoticeService NoticeService = new NoticeService();
    private final UserService userService = new UserService();

    @FXML private Label welcomeLabel;
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
        User user = SessionService.getUser();
        if (user != null && welcomeLabel != null) {
            welcomeLabel.setText(user.getFullName() + "님");
        }
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
        colIndividualSubmitted.setCellValueFactory(cellData ->
                new SimpleBooleanProperty(Boolean.TRUE.equals(cellData.getValue().isSubmitted()))
        );
        colIndividualSubmitted.setCellFactory(CheckBoxTableCell.forTableColumn(colIndividualSubmitted));

        colIndividualTitle.setCellValueFactory(cell ->
                new SimpleStringProperty("과제 " + cell.getValue().assignmentName())
        );

        colIndividualDate.setCellValueFactory(cell -> {
            LocalDate dt = LocalDate.from(cell.getValue().dueDate());
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
        colTeamSubmitted.setCellValueFactory(cellData ->
                new SimpleBooleanProperty(Boolean.TRUE.equals(cellData.getValue().isSubmitted()))
        );
        colTeamSubmitted.setCellFactory(CheckBoxTableCell.forTableColumn(colTeamSubmitted));

        colTeamTitle.setCellValueFactory(cell ->
                new SimpleStringProperty("과제 " + cell.getValue().assignmentId())
        );

        colTeamDate.setCellValueFactory(cell -> {
            LocalDate dt = LocalDate.from(cell.getValue().dueDate());
            return new SimpleObjectProperty<>(dt);
        });
        applyTeamColumnResizePolicy();

        teamTaskTable.setItems(teamTaskItems);
    }
    private void loadIndividualTableData() {
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
    }

    @FXML
    protected void onChangePasswordClick() {
        User user = SessionService.getUser();
        if (user == null) return;

        // 다이얼로그 생성
        Dialog<PasswordForm> dialog = new Dialog<>(); // 아래에 정의할 내부 클래스(Record) 사용
        dialog.setTitle("비밀번호 변경");
        dialog.setHeaderText("보안을 위해 현재 비밀번호와 새 비밀번호를 입력해주세요.");

        // 버튼 타입 설정
        ButtonType changeButtonType = new ButtonType("변경", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, ButtonType.CANCEL);

        // 그리드 레이아웃 및 입력 필드 3개 생성
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        PasswordField oldPasswordField = new PasswordField();
        oldPasswordField.setPromptText("현재 비밀번호");

        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("새로운 비밀번호");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("새로운 비밀번호 확인");

        grid.add(new Label("현재 비밀번호:"), 0, 0);
        grid.add(oldPasswordField, 1, 0);
        grid.add(new Label("새 비밀번호:"), 0, 1);
        grid.add(newPasswordField, 1, 1);
        grid.add(new Label("비밀번호 확인:"), 0, 2);
        grid.add(confirmPasswordField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // 결과 컨버터 (입력된 3개의 값을 묶어서 반환)
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == changeButtonType) {
                return new PasswordForm(
                        oldPasswordField.getText(),
                        newPasswordField.getText(),
                        confirmPasswordField.getText()
                );
            }
            return null;
        });

        // 다이얼로그 실행 및 검증 로직
        Optional<PasswordForm> result = dialog.showAndWait();

        result.ifPresent(form -> {
            // (1) 빈 값 검사
            if (form.oldPw.isEmpty() || form.newPw.isEmpty() || form.confirmPw.isEmpty()) {
                showAlert("오류", "모든 항목을 입력해주세요.");
                return;
            }

            // (2) 새 비밀번호 일치 여부 검사
            if (!form.newPw.equals(form.confirmPw)) {
                showAlert("오류", "새 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                return; // 여기서 중단 (서비스 호출 안 함)
            }

            // (3) 서비스 호출 (검증 통과 시 실행)
            try {
                // DTO 생성 (확인용 비번은 서비스에 보낼 필요 없음)
                ChangePasswordRequest request = new ChangePasswordRequest(user.getId(), form.oldPw, form.newPw);

                userService.changePassword(request);
                showAlert("성공", "비밀번호가 성공적으로 변경되었습니다.");

            } catch (Exception e) {
                // 기존 비밀번호 불일치 등의 에러 처리
                showAlert("변경 실패: ", e.getMessage());
            }
        });
    }
    // 다이얼로그 데이터를 전달하기 위한 임시 Record (클래스 내부에 작성)
    private record PasswordForm(String oldPw, String newPw, String confirmPw) {}

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
