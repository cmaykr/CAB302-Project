package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
public class SearchController {
    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;
    @FXML
    private Button viewItemsButton;
    @FXML
    protected void addItemsButton() throws IOException{
        Stage stage = (Stage)addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-item-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void backButtonClick() throws IOException{
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void viewItem() throws IOException{
        Stage stage = (Stage)viewItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("list-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }




}
