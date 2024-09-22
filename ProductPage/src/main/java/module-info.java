module org.example.productpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.productpage to javafx.fxml;
    exports org.example.productpage;
    exports org.example.productpage.controller;
    opens org.example.productpage.controller to javafx.fxml;
    exports org.example.productpage.model;
    opens org.example.productpage.model to javafx.fxml;
}