package com.kosa.classmanagerapp.controller.Admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class projectCreateController {
    public TextArea adminTextArea;
    public VBox middlebox;
    public TextField title_admin;
    public TextField content_admin;
    public CheckBox assignmentType_team;
    public CheckBox assignmentType_person;
    public DatePicker dueDate;


    // 버튼 클릭 시 호출
    @FXML
    private void handleFetchAssignment() {
        String title = title_admin.getText();
        String content = content_admin.getText();
        boolean isTeam = assignmentType_team.isSelected();
        boolean isPerson = assignmentType_person.isSelected();
        String deadline = (dueDate.getValue() != null) ? dueDate.getValue().toString() : "날짜 미선택";

        System.out.println("과제 제목: " + title);
        System.out.println("내용: " + content);
        System.out.println("팀과제: " + isTeam);
        System.out.println("개인과제: " + isPerson);
        System.out.println("마감 날짜: " + deadline);
    }


    @FXML
    protected void adminButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }
}