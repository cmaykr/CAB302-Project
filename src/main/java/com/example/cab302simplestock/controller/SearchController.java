package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class SearchController {
    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;
    @FXML
    private Button viewItemsButton;
    @FXML
    private Button goBack;

    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField productTypeTextField;
    @FXML
    private TextField productDescriptionTextField;
    @FXML
    private TextField productLocationTextField;
    @FXML
    private TextField productQuantityTextField;
    @FXML
    private TextField productPurchaseDateTextField;
    @FXML
    private RadioButton insuredRadioButton;
    @FXML
    private TextField productPriceTextField;



    @FXML
    protected void addItemsButton() throws IOException{
        Stage stage = (Stage)addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),SimpleStock.WIDTH,SimpleStock.HEIGHT);
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
    protected void viewItem() throws IOException{
        Stage stage = (Stage)viewItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("category-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void goBack() throws IOException {
        Stage stage = (Stage)goBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    public void handleReadText() {
        String productName = productNameTextField.getText();
        String productType = productTypeTextField.getText();
        String productDescription = productDescriptionTextField.getText();
        String productLocation = productLocationTextField.getText();
        double productQuantity = Double.parseDouble(productQuantityTextField.getText());
        String readDate = productPurchaseDateTextField.getText();
        LocalDate purchaseDate = LocalDate.parse(readDate);
        String readPrice = productPriceTextField.getText();
        double productPrice = Double.parseDouble(readPrice);
        boolean productInsured;
        productInsured = insuredRadioButton.isSelected();

        Item item = new Item(productName, purchaseDate, productPrice, productQuantity, productDescription,  productType,productType);

    }



}
