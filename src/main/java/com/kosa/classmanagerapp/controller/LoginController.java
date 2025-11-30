package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.model.UserAuthorization;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {
    public TextArea userTextArea;
    @FXML
    private StackPane root;

    @FXML
    public void initialize() throws Exception {
    }
    @FXML
    private TextField userNameField;  // ← userName 입력칸

    @FXML
    private PasswordField passwordField;; // password 입력


    @FXML
    protected void onLoginButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();

        // 화면에서 입력된 아이디 가져오기
        String inputUserName = userNameField.getText().trim();
        // 화면에서 입력된 비밀번호 가져오기
        String inputPassword = passwordField.getText().trim();

        if (inputUserName.isEmpty()) {
            Stage stage = (Stage) root.getScene().getWindow();
            Toast.show(stage, "아이디를 입력하세요", ToastColor.ERROR);
            return;
        }
        if (inputPassword.isEmpty()) {
            Stage stage = (Stage) root.getScene().getWindow();
            Toast.show(stage, "비밀번호를 입력하세요", ToastColor.ERROR);
            return;
        }

        // mybatis 세션 열기 (try-with-resource 로 자동 닫기)
        try (SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession()) {
            // mapper 가져오기
            UserMapper mapper = session.getMapper(UserMapper.class);
            // DB 에서 유저 조회
            User dbUser = mapper.getUserByUserName(inputUserName);
            // 유저가 존재하지 않는 경우 처리
            if (dbUser == null) {
                Stage stage = (Stage) root.getScene().getWindow();
                Toast.show(stage, "존재하지 않는 사용자입니다.", ToastColor.ERROR);
                return;
            }
            // 비밀번호 검증 (BCrypt)
            if (!BCrypt.checkpw(inputPassword, dbUser.getPasswordHash())) {
                Stage stage = (Stage) root.getScene().getWindow();
                Toast.show(stage, "비밀번호가 일치하지 않습니다.", ToastColor.ERROR);
                return;
            }
            // 로그인 성공 처리
            SessionService.setUser(dbUser);

            if (dbUser.getAuthorization() == UserAuthorization.ADMIN) {
                main.loadView("view/admin/admin-view.fxml");
            } else {
                Stage stage = (Stage) root.getScene().getWindow();
                Toast.show(stage, dbUser.getFullName() + "님 환영합니다!", ToastColor.SUCCESS);
                main.loadView("view/user/user-view.fxml");
            }
        } catch (Exception e) {
            System.out.println("로그인 에러: " + e.getMessage());
            Stage stage = (Stage) root.getScene().getWindow();
            Toast.show(stage, "로그인 처리 중 오류 발생", ToastColor.ERROR);
        }
    }
}
