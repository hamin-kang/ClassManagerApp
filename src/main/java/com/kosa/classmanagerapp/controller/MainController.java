package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() throws IOException {
        loadView("view/login/login-view.fxml");
    }

    public void loadView(String path) throws IOException {
        System.out.println("mainController " + MainApplication.class.getResource(path));
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(path));
        Parent view = loader.load();
        contentArea.getChildren().setAll(view);
    }

}