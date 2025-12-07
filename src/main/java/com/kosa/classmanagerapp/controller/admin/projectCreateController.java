package com.kosa.classmanagerapp.controller.admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.service.AssignmentService;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import com.sun.net.httpserver.Authenticator;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class projectCreateController {
    public TextArea adminTextArea;
    public VBox middlebox;
    public TextField title_admin;
    public TextField content_admin;
    public CheckBox assignmentType_team;
    public CheckBox assignmentType_person;
    public DatePicker dueDate;
    public Button createAssignment;

    // 과제 insert
    @FXML private TableView<Assignment> TaskTable;
    @FXML private TableColumn<Assignment, Integer> colId;
    @FXML private TableColumn<Assignment, String> colTitle;
    @FXML private TableColumn<Assignment, String> colContent;
    @FXML private TableColumn<Assignment, String> colType;
    @FXML private TableColumn<Assignment, String> colDueDate;


    private final AssignmentService assignmentService = new AssignmentService();
    private List<Assignment> allAssignments; //db에서 가져온 전체과제

    // 과제 생성 (insert)
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
        int submissionCount =  service.saveTransaction(assignment);

        if(submissionCount > 0) {
            Toast.show((Stage)createAssignment.getScene().getWindow(), "과제 생성 완료!", ToastColor.SUCCESS);
            allAssignments = assignmentService.findAll();
            loadAssignments();
            filterAssignments();
        }else{
            Toast.show((Stage)createAssignment.getScene().getWindow(), "과제 생성 실패!", ToastColor.ERROR);

        }
        System.out.println("과제 저장 완료: " + title);

    }

    // 뒤로가기 버튼
    @FXML
    protected void adminButtonClick(ActionEvent event) throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }

    //과제 드롭다운
    @FXML
    private ComboBox<String> taskComboBox;
    private void TaskList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
        taskComboBox.getItems().addAll(
                "개인과제","팀과제"
        );

        // 기본 선택값
        taskComboBox.getSelectionModel().selectFirst();
    }


    // 실행
    @FXML
    public void initialize() {
        // TableView 컬럼 연결
        colId.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getIdInt()));
        colTitle.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTitle()));
        colContent.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getContent()));
        colType.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getAssignmentType().name()));
        colDueDate.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(
                data.getValue().getDueDate() != null ? data.getValue().getDueDate().toString() : ""
        ));

        loadAssignments(); // DB에서 과제 불러오기
        TaskList();       // 드롭다운 초기화


        // ComboBox 초기화
//        taskComboBox.getItems().addAll("개인과제", "팀과제");
        taskComboBox.getSelectionModel().selectFirst();

        // 전체 과제 가져오기
        allAssignments = assignmentService.findAll();

        // 처음 로딩 시 선택된 타입으로 필터링
        filterAssignments();

        // ComboBox 선택 변경 시 필터 적용
        taskComboBox.setOnAction(e -> filterAssignments());


    }

    // 과제 끌고오기 (select)
    private void loadAssignments() {
        List<Assignment> assignments = assignmentService.findAll();
        TaskTable.getItems().setAll(assignments);
    }



    // ComboBox 선택에 따라 TableView 필터링
    private void filterAssignments() {
        String selected = taskComboBox.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        AssignmentType filterType = selected.equals("개인과제") ? AssignmentType.INDIVIDUAL : AssignmentType.TEAM;

        List<Assignment> filtered = allAssignments.stream()
                .filter(a -> a.getAssignmentType() == filterType)
                .toList();

        TaskTable.getItems().setAll(filtered);
    }

}