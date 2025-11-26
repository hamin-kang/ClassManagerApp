package com.kosa.classmanagerapp.util.Toast;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Toast {

    public static void show(Stage stage, String msg, String bgColorHex) {
        Popup popup = new Popup();

        Label label = new Label(msg);
        label.setStyle(
                "-fx-background-color: " + bgColorHex + ";" +
                        "-fx-text-fill: black;" +
                        "-fx-padding: 20px 40px;" +
                        "-fx-border-color: #d0d0d0;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-size: 18px;"
        );

        popup.getContent().add(label);

        popup.show(stage);

        popup.setX(stage.getX() + stage.getWidth() / 2 - label.getWidth() / 2);
        popup.setY(stage.getY() + 120);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(800), label);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setDelay(Duration.seconds(2));
        fadeOut.setOnFinished(e -> popup.hide());
        fadeOut.play();
    }
}