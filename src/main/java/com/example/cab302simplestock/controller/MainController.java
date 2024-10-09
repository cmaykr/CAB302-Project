package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button addToCategoryButton;
    @FXML
    private Button backButton;


    @FXML
    protected void addToCategoryButton() throws IOException {
        Stage stage = (Stage)addToCategoryButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void backButtonClick() throws IOException{
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

}
