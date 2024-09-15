package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    @FXML
    private Button addCategory;

    @FXML
    protected void onNextButtonClick() throws IOException {
        Stage stage = (Stage) addCategory.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view-category.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

}


