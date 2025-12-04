package com.kosa.classmanagerapp.controller;


import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.SubmissionContent;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.model.dto.submission.SubmissionStatusResponse;
import com.kosa.classmanagerapp.model.dto.submission.SubmissionRequest;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.service.submission.SubmissionService;
import com.kosa.classmanagerapp.service.submission.SubmissionContentService;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SubmissionDetailController {
    private final SubmissionService submissionService = AppContext.SUBMISSION_SERVICE;
    private final SubmissionContentService submissionContentService = new SubmissionContentService();
    private SubmissionStatusResponse sres;
    private SubmissionContent submissionContent;
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

        LocalDateTime due = s.dueDate();
        long dday = ChronoUnit.DAYS.between(LocalDate.now(), due);
        deadlineLabel.setText("마감 날짜 : D-" + dday);
        if (s.isClose()) {
            submitButton.setDisable(true);
            submitButton.setText("마감됨");
        }
        loadEditor();

        submissionContent = submissionContentService.findBySubmissionId(s.submissionId());

        if (submissionContent != null && submissionContent.getContent() != null) {
            System.out.println("Submission 내용 있음");

            WebEngine engine = editorWebView.getEngine();

            // editor 로드 완료 후 HTML 넣기 위해 listener 사용
            engine.getLoadWorker().stateProperty().addListener((obs, old, newState) -> {
                if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
                    engine.executeScript("editor.setHTML(`" + escape(submissionContent.getContent()) + "`);");
                }
            });
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

        WebEngine engine = editorWebView.getEngine();
        String content = (String) engine.executeScript("editor.getHTML();");

        Long submissionId = sres.submissionId();
        System.out.println("submissionId " + submissionId);

        int result;

        // 신규 제출 여부 판단
        if (submissionContent == null) {
            // 신규 제출
            SubmissionContent newContent = new SubmissionContent();
            newContent.setSubmissionId(submissionId);
            newContent.setContent(content);

            result = submissionContentService.save(newContent);

        } else {
            // 기존 제출
            submissionContent.setSubmissionId(submissionId);
            submissionContent.setContent(content);

            result = submissionContentService.update(submissionContent);
        }

        // Submission 테이블 업데이트
        if (result > 0) {
            SubmissionRequest req = new SubmissionRequest(
                    submissionId,
                    sres.assignmentId(),
                    user.getId(),
                    user.getTeamId(),
                    true,
                    content
            );

            result = submissionService.update(req);

            if (result < 1) {
                Toast.show((Stage) submitButton.getScene().getWindow(),
                        "제출 상태 변경 실패", ToastColor.SUCCESS);
            } else {
                Toast.show((Stage) submitButton.getScene().getWindow(),
                        "제출 되었습니다", ToastColor.SUCCESS);
            }
        } else {
            Toast.show((Stage) submitButton.getScene().getWindow(),
                    "내용 저장 실패", ToastColor.ERROR);
        }

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