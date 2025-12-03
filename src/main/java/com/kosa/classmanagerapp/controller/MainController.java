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
//    public void loadView(String path) throws IOException {
//        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(path));
//        Parent view = loader.load();
//        contentArea.getChildren().setAll(view);
//    }

    public void loadView(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(path));
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (Exception e) { // 임시로 예외를 잡아서 출력
            System.err.println("!!!!!!!!!!!!!!!! FXML 로딩 실패: " + path + " !!!!!!!!!!!!!!!!");
            e.printStackTrace(); // <--- 숨겨진 에러를 콘솔에 출력
            // 실패 시 관리자 뷰 로딩 대신 로그인 뷰로 돌아가는 것도 고려 가능

            // MainController.java에 throws IOException이 필요한 메서드에서 throws IOException을 제거해야 합니다.
        }
    }

    public void setContent(Parent root) {
        contentArea.getChildren().setAll(root);
    }
}