module org.example.simplestock {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.compiler;


    opens org.example.simplestock to javafx.fxml;
    exports org.example.simplestock;
    exports org.example.simplestock;
    opens org.example.simplestock to javafx.fxml;
    exports org.example.simplestock.Controller;
    opens org.example.simplestock.Controller to javafx.fxml;
}