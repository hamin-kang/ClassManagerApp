package com.kosa.classmanagerapp.controller.Admin;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class teamCreateConroller {

    @FXML
    protected void adminButtonClick(ActionEvent event) throws Exception {
        MainController main = MainApplication.getMainController();
        main.loadView("view/admin/admin-view.fxml");

    }
}
