module com.kosa.classmanagerapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.mybatis;

    opens com.kosa.classmanagerapp to javafx.fxml;
    exports com.kosa.classmanagerapp;
}