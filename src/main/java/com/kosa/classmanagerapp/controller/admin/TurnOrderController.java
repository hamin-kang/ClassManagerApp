package com.kosa.classmanagerapp.controller.admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.Team;
import com.kosa.classmanagerapp.model.assignment.Assignment;
import com.kosa.classmanagerapp.model.assignment.AssignmentType;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurnOrderController {

    @FXML private ComboBox<Assignment> teamAssignmentComboBox;
    @FXML private TextArea resultArea;

    // 섞인 순서를 임시 저장할 문자열
    private String currentOrderString = null;

    // FXML이 로드될 때 자동으로 실행됨 (initTurnOrderPage 메서드 필요 없음)
    @FXML
    public void initialize() {
        loadTeamAssignments();

        // 리스너 등록
        teamAssignmentComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadSavedOrder(newVal);
            }
        });
    }

    private void loadTeamAssignments() {
        // 1. 팀 과제(TEAM)만 DB에서 가져오기
        List<Assignment> allAssignments = AppContext.ASSIGNMENT_SERVICE.findAll();
        List<Assignment> teamAssignments = allAssignments.stream()
                .filter(a -> a.getAssignmentType() == AssignmentType.TEAM)
                .toList();

        // 2. 콤보박스 설정
        teamAssignmentComboBox.getItems().setAll(teamAssignments);

        // 3. 화면 표시 설정 (제목만 보이게)
        teamAssignmentComboBox.setConverter(new StringConverter<Assignment>() {
            @Override
            public String toString(Assignment assignment) {
                return (assignment != null) ? assignment.getTitle() : null;
            }
            @Override
            public Assignment fromString(String string) { return null; }
        });

        // 셀 팩토리 설정
        teamAssignmentComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Assignment item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? null : item.getTitle());
            }
        });
        teamAssignmentComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Assignment item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? null : item.getTitle());
            }
        });
    }

    @FXML
    protected void onShuffleButtonClick() {
        if (teamAssignmentComboBox.getValue() == null) {
            Toast.show((Stage) resultArea.getScene().getWindow(), "과제를 먼저 선택해주세요.", ToastColor.ERROR);
            return;
        }

        List<Team> teams = new ArrayList<>(AppContext.TEAM_SERVICE.findAll());
        if (teams.isEmpty()) {
            resultArea.setText("등록된 팀이 없습니다.");
            return;
        }

        Collections.shuffle(teams);

        StringBuilder sb = new StringBuilder();
        List<String> teamIds = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            sb.append(String.format("%d번 순서 : %s\n", i + 1, t.getTeamName()));
            teamIds.add(String.valueOf(t.getId()));
        }

        resultArea.setText(sb.toString());
        currentOrderString = String.join(",", teamIds);
    }

    @FXML
    protected void onSaveOrderClick() {
        Assignment selectedAssignment = teamAssignmentComboBox.getValue();
        if (selectedAssignment == null || currentOrderString == null) {
            Toast.show((Stage) resultArea.getScene().getWindow(), "먼저 순서를 뽑아주세요.", ToastColor.ERROR);
            return;
        }

        AppContext.ASSIGNMENT_SERVICE.updatePresentationOrder(selectedAssignment.getId(), currentOrderString);

        // [중요] 메모리 객체 업데이트
        selectedAssignment.setPresentationdOrderTeamId(currentOrderString);

        Toast.show((Stage) resultArea.getScene().getWindow(), "발표 순서가 저장되었습니다!", ToastColor.SUCCESS);
    }

    private void loadSavedOrder(Assignment assignment) {
        String savedOrder = assignment.getPresentationdOrderTeamId();

        if (savedOrder == null || savedOrder.trim().isEmpty()) {
            resultArea.setText("아직 발표 순서가 정해지지 않았습니다.");
            currentOrderString = null;
            return;
        }

        currentOrderString = savedOrder;
        List<Team> allTeams = AppContext.TEAM_SERVICE.findAll();
        String[] teamIds = savedOrder.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("[ 저장된 발표 순서입니다 ]\n\n");

        for (int i = 0; i < teamIds.length; i++) {
            try {
                Long teamId = Long.parseLong(teamIds[i].trim());
                String teamName = allTeams.stream()
                        .filter(t -> t.getId().equals(teamId))
                        .map(Team::getTeamName)
                        .findFirst()
                        .orElse("(삭제된 팀)");
                sb.append(String.format("%d번 순서 : %s\n", i + 1, teamName));
            } catch (NumberFormatException e) { /* 무시 */ }
        }
        resultArea.setText(sb.toString());
    }

    // 뒤로가기 버튼용
    @FXML
    protected void adminButtonClick() throws IOException {
        MainApplication.getMainController().loadView("view/admin/admin-view.fxml");
    }
}