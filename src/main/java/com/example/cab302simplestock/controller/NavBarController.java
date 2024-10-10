package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteItemDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import com.example.cab302simplestock.model.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class NavBarController {
    @FXML
    private Button goHome;

    @FXML
    private Button goCategories;

    @FXML
    private Button goGroups;

    @FXML
    private Button goAccounts;

    @FXML
    private Button goSettings;

    @FXML
    public void goToHome(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goHome.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    public void goToCategories(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goCategories.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    public void goToGroups(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goCategories.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("groups-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    public void goToAccounts(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goCategories.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("accounts-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    public void goToSettings(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goCategories.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("settings-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }



}
