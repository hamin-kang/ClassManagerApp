package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.model.UserAuthorization;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {
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
        //-start db가 없어서 더미로 생성
        User user = new User();
        if(username.equalsIgnoreCase("admin")){
            user.setId(2L);
            user.setUserName("admin");
            user.setAuthorization(UserAuthorization.ADMIN);
        }else if(username.equalsIgnoreCase("user")){
            user.setId(1L);
            user.setUserName("user");
            user.setAuthorization(UserAuthorization.USER);
        }
        //-end

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
