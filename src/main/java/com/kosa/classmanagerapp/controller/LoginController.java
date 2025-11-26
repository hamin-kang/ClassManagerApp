package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
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

        if (username.equalsIgnoreCase("admin")) {
            main.loadView("view/admin/admin-view.fxml");
        } else {
            Stage stage = (Stage) root.getScene().getWindow();
            Toast.show(stage,"사용자1 안녕하세요", ToastColor.SUCCESS);
            main.loadView("view/user/user-view.fxml");
        }
    }
}
