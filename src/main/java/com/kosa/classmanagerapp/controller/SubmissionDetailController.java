package com.kosa.classmanagerapp.controller;


import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.Submission;
import com.kosa.classmanagerapp.model.dto.SubmissionStatusResponse;
import com.kosa.classmanagerapp.service.SubmissionService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SubmissionDetailController {
    private SubmissionStatusResponse submission;
    private final SubmissionService submissionService = AppContext.SUBMISSION_SERVICE;

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
        this.submission = s;

        titleLabel.setText("개인 과제 : 과제" + s.getAssignmentId());

//        LocalDate due = s.getDueDate();  // 마감일 필드 있다고 가정
//        long dday = ChronoUnit.DAYS.between(LocalDate.now(), due);
        deadlineLabel.setText("마감 날짜 : D-3");

//        deadlineLabel.setText("마감 날짜 : D-3" + dday);

        loadEditor();
    }
    @FXML
    private void handleSubmit() {
        WebEngine engine = editorWebView.getEngine();

        System.out.println("제출된 내용:");
        String content = (String) engine.executeScript("editor.getHTML();");
        System.out.println(content);
        // TODO: DB 저장 처리 submission.setContent(content); submissionService.update(submission);
    }
}