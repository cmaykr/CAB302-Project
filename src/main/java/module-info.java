module com.example.cab302simplestock {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.smartcardio;
    requires org.xerial.sqlitejdbc;
    requires javafx.graphics;
    requires java.desktop;
    requires org.apache.commons.compress;


    opens com.example.cab302simplestock.controller to javafx.fxml;
    exports com.example.cab302simplestock;
    exports com.example.cab302simplestock.model;
    exports com.example.cab302simplestock.controller;
    exports com.example.cab302simplestock.model.InterfaceDAOs;
    exports com.example.cab302simplestock.model.SqliteDAOs;
    exports com.example.cab302simplestock.model.MockDAOs;
    exports com.example.cab302simplestock.model.ManagerInterfaces;
    exports com.example.cab302simplestock.model.Managers;
}