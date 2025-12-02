package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminController {
    public TextArea adminTextArea;
    // --- 발표 순서 뽑기 관련 변수 ---
    @FXML private ComboBox<Assignment> teamAssignmentComboBox; // String이 아니라 객체 자체를 담음
    @FXML private TextArea resultArea;


    // 섞인 순서를 임시 저장할 문자열
    private String currentOrderString = null;

    // 화면이 로드될 때 실행됨
    public void initTurnOrderPage() {
        if (teamAssignmentComboBox == null) return;

        // 팀 과제(TEAM)만 DB에서 가져오기
        List<Assignment> allAssignments = AppContext.ASSIGNMENT_SERVICE.findAll();
        List<Assignment> teamAssignments = allAssignments.stream()
                .filter(a -> a.getAssignmentType() == AssignmentType.TEAM)
                .toList();

        // 콤보박스에 추가
        teamAssignmentComboBox.getItems().setAll(teamAssignments);

        // 콤보박스에 '객체 주소' 대신 '과제 제목(Title)'이 보이도록 설정
        teamAssignmentComboBox.setConverter(new StringConverter<Assignment>() {
            @Override
            public String toString(Assignment assignment) {
                return (assignment != null) ? assignment.getTitle() : null;
            }

            @Override
            public Assignment fromString(String string) {
                return null; // 역변환은 필요 없음
            }
        });

        // 3. 콤보박스에 표시될 이름 설정 (과제 제목)
        teamAssignmentComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Assignment item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) setText(null);
                else setText(item.getTitle());
            }
        });
        teamAssignmentComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Assignment item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) setText(null);
                else setText(item.getTitle());
            }
        });

        teamAssignmentComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadSavedOrder(newVal);
            }
        });
    }

    @FXML
    protected void onShuffleButtonClick() {
        if (teamAssignmentComboBox.getValue() == null) {
            Toast.show((Stage) resultArea.getScene().getWindow(), "과제를 먼저 선택해주세요.", ToastColor.ERROR);
            return;
        }

        // 1. 전체 팀 목록 가져오기
        List<Team> teams = new ArrayList<>(AppContext.TEAM_SERVICE.findAll());

        if (teams.isEmpty()) {
            resultArea.setText("등록된 팀이 없습니다.");
            return;
        }

        // 2. 셔플 (랜덤 섞기)
        Collections.shuffle(teams);

        // 3. 결과 텍스트 생성 및 화면 표시
        StringBuilder sb = new StringBuilder();
        List<String> teamIds = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            sb.append(String.format("%d번 순서 : %s\n", i + 1, t.getTeamName()));
            teamIds.add(String.valueOf(t.getId())); // ID 저장
        }

        resultArea.setText(sb.toString());

        // 4. DB 저장용 문자열 생성 ("1,3,2,5,4" 형태)
        currentOrderString = String.join(",", teamIds);
    }

    @FXML
    protected void onSaveOrderClick() {
        Assignment selectedAssignment = teamAssignmentComboBox.getValue();
        if (selectedAssignment == null || currentOrderString == null) {
            Toast.show((Stage) resultArea.getScene().getWindow(), "먼저 순서를 뽑아주세요.", ToastColor.ERROR);
            return;
        }

        // 5. 서비스 호출하여 DB 업데이트
        AppContext.ASSIGNMENT_SERVICE.updatePresentationOrder(selectedAssignment.getId(), currentOrderString);

        // DB에 저장한 값을 현재 선택된 자바 객체에도 바로 심어줘야 함
        selectedAssignment.setPresentationdOrderTeamId(currentOrderString);

        Toast.show((Stage) resultArea.getScene().getWindow(), "발표 순서가 저장되었습니다!", ToastColor.SUCCESS);
    }

    @FXML
    protected void teamCreateButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();

         main.loadView("view/admin/team-create.fxml");

    }

    @FXML
    protected void taskCreateButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/task-create.fxml");

    }

    @FXML
    protected void turnOrderButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/turn-order.fxml");
    }

    @FXML
    protected void adminButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }

    @FXML
    private ComboBox<String> teamComboBox;
    private void loadTeamList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
        if (teamComboBox == null) return;
        teamComboBox.getItems().addAll(
                "1팀", "2팀", "3팀", "4팀", "5팀"
        );

        // 기본 선택값
        teamComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private ComboBox<String> taskComboBox; //과제 드롭다운
    private void TaskList() {
        // 실제로 팀을 DB나 서비스에서 가져오는 것처럼 구성 가능
        // 여기서는 예제로 1~5팀 추가
        if (taskComboBox == null) return;
        taskComboBox.getItems().addAll(
                "알고리즘", "미니과제", "발표", "UML", "final프로젝트"
        );

        // 기본 선택값
        taskComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private TableView<?> TaskTable;

    @FXML
    private TableView<?> attendanceTable;

    @FXML
    public void initialize() { // 드롭다운

        if (TaskTable != null) {
            // TableView 폭에 맞춰 컬럼 폭 자동 조절
            TaskTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }
        if (attendanceTable != null) {
            attendanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        }

        // FXML 로딩 후 안전하게 실행
        Platform.runLater(() -> {
            loadTeamList();
            TaskList();
            initTurnOrderPage(); // 추가: 발표순서 페이지 초기화
        });
    }

    private void loadSavedOrder(Assignment assignment) {
        // 저장된 순서 문자열 가져오기
        String savedOrder = assignment.getPresentationdOrderTeamId();

        // 저장된 게 없으면 메시지 띄우고 종료
        if (savedOrder == null || savedOrder.trim().isEmpty()) {
            resultArea.setText("아직 발표 순서가 정해지지 않았습니다.");
            currentOrderString = null;
            return;
        }

        // 현재 이 과제의 순서 문자열로 전역 변수 업데이트 (나중에 덮어쓰기 할 때 필요)
        currentOrderString = savedOrder;

        // 팀 정보 가져오기 (ID로 이름을 찾기 위해)
        List<Team> allTeams = AppContext.TEAM_SERVICE.findAll();

        // 저장된 문자열("1,3,2")을 분해해서 화면에 출력할 텍스트 만들기
        String[] teamIds = savedOrder.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("[ 저장된 발표 순서입니다 ]\n\n");

        for (int i = 0; i < teamIds.length; i++) {
            try {
                Long teamId = Long.parseLong(teamIds[i].trim());

                // 팀 리스트에서 해당 ID를 가진 팀 찾기
                String teamName = allTeams.stream()
                        .filter(t -> t.getId().equals(teamId))
                        .map(Team::getTeamName)
                        .findFirst()
                        .orElse("(삭제된 팀)");

                sb.append(String.format("%d번 순서 : %s\n", i + 1, teamName));

            } catch (NumberFormatException e) {
                // e
            }
        }

        resultArea.setText(sb.toString());
    }

}
