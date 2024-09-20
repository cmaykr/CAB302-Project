module com.example.cab302simplestock {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.smartcardio;


    opens com.example.cab302simplestock.controller to javafx.fxml;
    exports com.example.cab302simplestock;
    exports com.example.cab302simplestock.model;
    exports com.example.cab302simplestock.controller;
}