package com.kosa.classmanagerapp.controller.Admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    // 뒤로가기 버튼
    @FXML
    protected void adminButtonClick(ActionEvent event) throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }

    @FXML
    private ComboBox<String> taskComboBox; //과제 드롭다운
    private void TaskList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
        taskComboBox.getItems().addAll(
                "알고리즘", "미니과제", "발표", "UML", "final프로젝트"
        );

        // 기본 선택값
        taskComboBox.getSelectionModel().selectFirst();
    }
}