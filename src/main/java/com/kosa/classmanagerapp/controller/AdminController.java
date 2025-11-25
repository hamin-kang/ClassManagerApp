package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class AdminController {
    public TextArea adminTextArea;

    @FXML
    protected void teamCreateButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();

         main.loadView("view/admin/team-create.fxml");

    }

    @FXML
    protected void taskCreateButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/task-create.fxml");

    }

    @FXML
    protected void turnOrderButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/task-create.fxml");

    }

    @FXML
    protected void adminButtonClick() throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }




}
