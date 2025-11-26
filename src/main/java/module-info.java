module com.kosa.classmanagerapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.mybatis;
    requires javafx.graphics;

    // mybatis 가 model, util, controller 에 접근하여 매핑할 수 있도록 opens 추가
    opens com.kosa.classmanagerapp to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.util to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.model to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.controller to javafx.fxml, org.mybatis;

    exports com.kosa.classmanagerapp;
    exports com.kosa.classmanagerapp.controller;

}
