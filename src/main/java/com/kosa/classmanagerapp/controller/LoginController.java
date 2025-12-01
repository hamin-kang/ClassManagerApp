package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.dao.UserMapper;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.model.UserAuthorization;
import com.kosa.classmanagerapp.service.AuthService;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class LoginController {
    @FXML
    private StackPane root;

    @FXML
    public void initialize() throws Exception {
    }
    @FXML
    private TextField userNameField;  // userName 입력

    @FXML
    private PasswordField passwordField;; // password 입력

    private final AuthService authService = new AuthService();

    @FXML
    protected void onLoginButtonClick() throws Exception {
        // 화면에서 입력된 아이디 가져오기
        String inputUserName = userNameField.getText().trim();
        // 화면에서 입력된 비밀번호 가져오기
        String inputPassword = passwordField.getText().trim();
        Stage stage = (Stage) root.getScene().getWindow();

        if (inputUserName.isEmpty()) {
            Toast.show(stage, "아이디를 입력하세요", ToastColor.ERROR);
            return;
        }
        if (inputPassword.isEmpty()) {
            Toast.show(stage, "비밀번호를 입력하세요", ToastColor.ERROR);
            return;
        }

        // 서비스 호출 및 결과 처리
        try {
            User dbUser = authService.login(inputUserName, inputPassword);

            // 세션 설정 및 화면 전환
            SessionService.setUser(dbUser);
            MainController main = MainApplication.getMainController();

            if (dbUser.getAuthorization() == UserAuthorization.ADMIN) {
                main.loadView("view/admin/admin-view.fxml");
            } else {
                Toast.show(stage, dbUser.getFullName() + "님 환영합니다!", ToastColor.SUCCESS);
                main.loadView("view/user/user-view.fxml");
            }

        } catch (Exception e) {
            // 서비스에서 던진 예외 메시지("존재하지 않는...", "비밀번호가...")를 그대로 출력
            System.out.println("로그인 실패: " + e.getMessage());
            Toast.show(stage, e.getMessage(), ToastColor.ERROR);
        }
    }

    @FXML
    protected void onSignupButtonClick() throws IOException {
        MainController main = MainApplication.getMainController();
        main.loadView("view/login/signup-view.fxml");
    }
}
