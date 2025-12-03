package com.kosa.classmanagerapp.controller.admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.service.AssignmentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        AssignmentType type = isTeam ? AssignmentType.TEAM : AssignmentType.INDIVIDUAL;
        boolean isPerson = assignmentType_person.isSelected();
        boolean isClose = false; // 필요에 따라 체크박스로 지정 가능
        LocalDateTime due = (dueDate.getValue() != null) ?
                dueDate.getValue().atTime(23, 59, 59) : LocalDateTime.now().plusDays(7);

        Assignment assignment = Assignment.builder()
                .title(title)
                .content(content)
                .creatorId(1L) // 현재 로그인한 사용자 ID로 변경
                .assignmentType(type)
                .isClose(isClose)
                .dueDate(due)
                .build();

        AssignmentService service = new AssignmentService();
        service.save(assignment);

        System.out.println("과제 저장 완료: " + title);

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
                "개인과제","팀과제"
        );

        // 기본 선택값
        taskComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize() { // 드롭다운
        TaskList();

    }
}