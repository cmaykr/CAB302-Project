package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class listViewController {

    @FXML
    private Button goBack;
    @FXML
    protected void goBack() throws IOException {
        Stage stage = (Stage)goBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

}
