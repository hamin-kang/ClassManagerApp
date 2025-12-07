package com.kosa.classmanagerapp;

import com.kosa.classmanagerapp.controller.MainController;
import com.kosa.classmanagerapp.global.AppContext;
import com.kosa.classmanagerapp.global.initData.InitDataDB;
import com.kosa.classmanagerapp.util.SqlSessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    private static MainController mainController;

    @Override
    public void start(Stage stage) throws IOException { // 앱 실행됐을 때 처음 나오는 화면
        // DB 연결 테스트 코드: 클래스를 참조하여 static 블록을 강제 실행
        // 이 코드를 추가하면 start() 메서드 초기에 DB 연결 시도
        SqlSessionManager.getSqlSessionFactory();

        try {
            Image appIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/kosa.png")));
            stage.getIcons().add(appIcon);
        } catch (Exception e) {
            System.err.println("App Icon load failed: " + e.getMessage());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(
//                MainApplication.class.getResource("main-view.fxml")
                MainApplication.class.getResource("view/main-view.fxml")

        );

        Parent root = fxmlLoader.load();
        mainController = fxmlLoader.getController();

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("KOSA");
        stage.setScene(scene);
        stage.show();
    }
    public static MainController getMainController() {
        return mainController;
    }
    public static void main(String[] args) {
        launch();
    }
}