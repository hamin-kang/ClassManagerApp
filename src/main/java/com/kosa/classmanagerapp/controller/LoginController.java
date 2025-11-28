package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.model.UserAuthorization;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.service.UserService;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {
    private final UserService userService = AppContext.USER_SERVICE;

    public TextArea userTextArea;
    @FXML
    private StackPane root;

    @FXML
    public void initialize() throws Exception {
    }
    @FXML
    private TextField usernameField;  // ← username 입력칸


    @FXML
    protected void onLoginButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();

        System.out.println("onLoginButtonClick username " + usernameField);
        String username = usernameField.getText().trim();
        User user = userService.findByUserName(username);

        if (user.getAuthorization() == UserAuthorization.ADMIN) {
            SessionService.setUser(user);
            main.loadView("view/admin/admin-view.fxml");
        } else {
            SessionService.setUser(user);
            Stage stage = (Stage) root.getScene().getWindow();
            Toast.show(stage,user.getUserName() + " 안녕하세요", ToastColor.SUCCESS);
            main.loadView("view/user/user-view.fxml");
        }
    }
}
