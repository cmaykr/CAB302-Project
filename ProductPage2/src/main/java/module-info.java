module org.example.productpage2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.productpage2 to javafx.fxml;
    exports org.example.productpage2;
}