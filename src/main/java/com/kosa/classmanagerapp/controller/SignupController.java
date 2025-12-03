package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.dto.auth.SignupRequest;
import com.kosa.classmanagerapp.model.entity.User;
import com.kosa.classmanagerapp.service.auth.UserService;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML private StackPane root;
    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField fullNameField;
    @FXML private DatePicker birthdayPicker;

    private final UserService userService = new UserService();

    @FXML
    protected void onSignupClick() throws IOException {
        // 입력값 검증
        if (userNameField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                fullNameField.getText().isEmpty() || birthdayPicker.getValue() == null) {
            Toast.show((Stage) root.getScene().getWindow(), "모든 정보를 입력해주세요.", ToastColor.ERROR);
            return;
        }
        // DTO 생성하여 데이터 넣기
        SignupRequest request = new SignupRequest(
                userNameField.getText(),
                passwordField.getText(),
                fullNameField.getText(),
                birthdayPicker.getValue()
        );

        // 서비스 호출(회원가입)
        boolean isSuccess = userService.signup(request);

        if (isSuccess) {
            Toast.show((Stage) root.getScene().getWindow(), "회원가입 성공! 로그인 해주세요.", ToastColor.SUCCESS);
            // 로그인 화면으로 이동
            MainApplication.getMainController().loadView("view/login/login-view.fxml");
        } else {
            Toast.show((Stage) root.getScene().getWindow(), "회원가입 실패", ToastColor.ERROR);
        }
    }

    @FXML
    protected void onCancelClick() throws IOException {
        // 취소 시 로그인 화면으로 복귀
        MainApplication.getMainController().loadView("view/login/login-view.fxml");
    }
}
