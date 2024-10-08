package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.HelloApplication;
import com.example.cab302simplestock.model.IContactDAO;
import com.example.cab302simplestock.model.SqliteContactDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItemController {
    private IContactDAO contactDao;
    public AddItemController(){
        contactDao = new SqliteContactDAO();
    }

    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;
    @FXML
    protected void addItemsButton() throws IOException {
        Stage stage = (Stage)addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-item-view.fxml"));
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

    @FXML
    protected void addItemToList() {
        // TODO: connect to sqlite db and create an item

    }

}
