module com.kosa.classmangerapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.kosa.classmangerapp to javafx.fxml;
    exports com.kosa.classmangerapp;
}