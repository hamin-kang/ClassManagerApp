module com.kosa.classmanagerapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.mybatis;
    requires javafx.graphics;
    requires java.desktop;
    requires jbcrypt;
    requires javafx.base;
    requires mysql.connector.j;

    requires javafx.web;
    requires jdk.jsobject;

    // mybatis 가 model, util, controller 에 접근하여 매핑할 수 있도록 opens 추가
    opens com.kosa.classmanagerapp to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.util to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.model to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.controller to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.controller.admin to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.dao to org.mybatis;
    opens com.kosa.classmanagerapp.util.Toast to javafx.fxml;
    opens com.kosa.classmanagerapp.model.dto.submission;


    exports com.kosa.classmanagerapp;
    exports com.kosa.classmanagerapp.controller;
    exports com.kosa.classmanagerapp.model.entity;

    opens com.kosa.classmanagerapp.model.assignment to javafx.fxml, org.mybatis;
    opens com.kosa.classmanagerapp.model.attendance to javafx.fxml, org.mybatis, javafx.base;
    opens com.kosa.classmanagerapp.model.entity to javafx.fxml, org.mybatis;
    exports com.kosa.classmanagerapp.model.dto.submission;
    opens com.kosa.classmanagerapp.dao.submission to org.mybatis;

}
