package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.service.SessionService;
import com.kosa.classmanagerapp.util.Toast.Toast;
import com.kosa.classmanagerapp.util.Toast.ToastColor;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    @FXML
    public void initialize() throws Exception {
        User user = SessionService.getUser();
        if (user != null) {
            System.out.println("Current User " + user.getUserName());
        }
    }
    @FXML
    protected void logoutClick() throws Exception {
        SessionService.clear();

        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void submitPrivate() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

    @FXML
    protected void submitTeam() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/login/login-view.fxml");

    }

}
