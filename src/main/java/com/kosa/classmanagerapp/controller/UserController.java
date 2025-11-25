package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserController {
    public TextArea userTextArea;
    @FXML
    private AnchorPane root;

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
            main.loadView("view/user/user-view.fxml");
        }
    }

}
