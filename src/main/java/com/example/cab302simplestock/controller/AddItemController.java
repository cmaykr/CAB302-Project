package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.SqliteConnection;
import com.example.cab302simplestock.model.IItemDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.sqlite.SQLiteConnection;

import java.io.IOException;

public class AddItemController {
    private IItemDAO itemDao;
    public AddItemController(){
        itemDao = new SQLiteConnection();
    }

    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;
    @FXML
    protected void addItemsButton() throws IOException {
        Stage stage = (Stage)addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("add-item-view.fxml"));
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

    @FXML
    protected void addItemToList() {
        // TODO: connect to sqlite db and create an item

    }

}
