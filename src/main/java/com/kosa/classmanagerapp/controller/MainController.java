package com.kosa.classmanagerapp.controller;

import com.kosa.classmanagerapp.MainApplication;
import com.kosa.classmanagerapp.model.User;
import com.kosa.classmanagerapp.model.UserAuthorization;
import com.kosa.classmanagerapp.service.SessionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentArea;
    @FXML
    private HBox headerArea;

    @FXML
    public void initialize() throws IOException {
        //세션있나 확인
        User user = SessionService.getUser();
        if(user == null){
            //없으면 login view
            loadView("view/login/login-view.fxml");
        }else{
            //있으면 권한에 따라 페이지 로드
            if(user.getAuthorization() == UserAuthorization.ADMIN){
                loadView("view/admin/admin-view.fxml");
            }else if(user.getAuthorization() == UserAuthorization.USER){
                loadView("view/user/user-view.fxml");
            }
        }

    }
    @FXML
    private void onHeaderClick(MouseEvent event) throws IOException {
        //세션있나 확인
        User user = SessionService.getUser();
        if(user == null){
            //없으면 login view
            loadView("view/login/login-view.fxml");
        }else{
            //있으면 권한에 따라 페이지 로드
            if(user.getAuthorization() == UserAuthorization.ADMIN){
                loadView("view/admin/admin-view.fxml");
            }else if(user.getAuthorization() == UserAuthorization.USER){
                loadView("view/user/user-view.fxml");
            }
        }
    }
    public void loadView(String path) throws IOException {
        System.out.println("mainController " + MainApplication.class.getResource(path));
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(path));
        Parent view = loader.load();
        contentArea.getChildren().setAll(view);
    }

}