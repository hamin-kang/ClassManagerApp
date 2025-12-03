package com.kosa.classmanagerapp.controller;


import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.model.dto.SubmissionStatusResponse;
import com.kosa.classmanagerapp.model.dto.SubmissionRequest;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.service.SubmissionService;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SubmissionDetailController {
    private final SubmissionService submissionService = AppContext.SUBMISSION_SERVICE;
    private SubmissionStatusResponse sres;
    User user = SessionService.getUser();

    @FXML private Button submitButton;
    @FXML private Label titleLabel;
    @FXML private WebView editorWebView;
    @FXML private Label deadlineLabel;


    @FXML
    public void initialize() throws Exception {
        loadEditor();
    }
    private void loadEditor() {
        WebEngine engine = editorWebView.getEngine();
        engine.load(getClass().getResource("/web/editor.html").toExternalForm());

    }

    public void setSubmissionDetail(SubmissionStatusResponse s) {
        this.sres = s;
        titleLabel.setText("과제 " + s.assignmentId() + " : " + s.assignmentName());

        LocalDate due = s.dueDate();
        long dday = ChronoUnit.DAYS.between(LocalDate.now(), due);
        deadlineLabel.setText("마감 날짜 : D-" + dday);
        if (s.isClose()) {
            submitButton.setDisable(true);
            submitButton.setText("마감됨");
        }
        loadEditor();

        if (s.submittedId() != null) {
            Submission existing = submissionService.findById(s.submittedId());

            if (existing != null && existing.getContent() != null) {
                WebEngine engine = editorWebView.getEngine();

                // editor 로드 완료 후 HTML 넣기 위해 listener 사용
                engine.getLoadWorker().stateProperty().addListener((obs, old, newState) -> {
                    if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                        engine.executeScript("editor.setHTML(`" + escape(existing.getContent()) + "`);");
                    }
                });
            }
        }
    }
    private String escape(String html) {
        return html
                .replace("\\", "\\\\")
                .replace("`", "\\`")
                .replace("$", "\\$");
    }
    @FXML
    private void handleSubmit() {
        System.out.println("SubmissionDetailController::handleSubmit");
        WebEngine engine = editorWebView.getEngine();

        System.out.println("제출된 내용:");
        String content = (String) engine.executeScript("editor.getHTML();");
        System.out.println(content);

        SubmissionRequest sq = new SubmissionRequest(
                sres.submittedId(),
                sres.assignmentId(),
                user.getId(),
                user.getTeamId(),
                content);

        int result = submissionService.submitAssignment(sq);
        Stage stage = (Stage) submitButton.getScene().getWindow();

        if(result < 1){
            Toast.show(stage, "제출 실패", ToastColor.ERROR);
            return;
        }
        Toast.show(stage, "제출 되었습니다", ToastColor.SUCCESS);
        moveUserPage();

    }
    private void moveUserPage(){
        javafx.application.Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/kosa/classmanagerapp/view/user/user-view.fxml")
                );

                Parent root = loader.load();

                MainController main = MainApplication.getMainController();
                main.setContent(root);


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}