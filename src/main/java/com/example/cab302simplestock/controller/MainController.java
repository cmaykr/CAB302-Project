package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;
    @FXML
    protected void addItemsButton() throws IOException {
        Stage stage = (Stage)addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void backButtonClick() throws IOException{
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

}
