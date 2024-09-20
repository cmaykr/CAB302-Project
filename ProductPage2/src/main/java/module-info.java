module org.example.productpage2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.productpage2 to javafx.fxml;
    exports org.example.productpage2;
}