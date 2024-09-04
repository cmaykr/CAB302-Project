module com.example.cab302simplestock {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cab302simplestock to javafx.fxml;
    exports com.example.cab302simplestock;
}