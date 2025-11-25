module com.kosa.classmanagerapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.mybatis;
    requires org.postgresql.jdbc;
    requires javafx.graphics;

    opens com.kosa.classmanagerapp to javafx.fxml;
    opens com.kosa.classmanagerapp.util to javafx.fxml;
    opens com.kosa.classmanagerapp.model to javafx.fxml;

    exports com.kosa.classmanagerapp;
    exports com.kosa.classmanagerapp.controller;
    opens com.kosa.classmanagerapp.controller to javafx.fxml;

}
