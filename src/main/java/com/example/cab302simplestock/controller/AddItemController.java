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

import java.io.IOException;
import java.time.LocalDate;

/**
 * Controller class handling the addition of new items to the system.
 * Manages the user interface and logic for adding items, including form for validation and communication
 * with the database through the DAO layer.
 *
 */
public class AddItemController {
    /**
     * DAO interface for performing item-related database operation.
     */
    private IItemDAO itemDao;

    /**
     * Constructgor for the AddItemContorller.
     * Initialises the DAO implementation for interacting with the database.
     */
    public AddItemController(){
        itemDao = new SqliteItemDAO();
    }

    // Link to the FXML fields
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
    private RadioButton notInsuredRadioButton;
    @FXML
    private TextField productPriceTextField;

    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;

    /**
     * Handles the action when the "Add Items" button is clicked.
     * Transitions the user to the "Add Item" view.
     *
     * @throws IOException if there is an error while loading the FXML file for the
     * add item view.
     */
    @FXML
    protected void addItemsButton() throws IOException {
        Stage stage = (Stage)addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("add-item-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Handles the action when the "Back" button is clicked.
     * Navigates the user back to the "search" view.
     * @throws IOException if there is an Error while loading the FXML file for the search view.
     */
    @FXML
    protected void backButtonClick() throws IOException{
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }
    /**
     * Retrieves input data from the form, validates it, creates an Item object,
     * and saves the item to the database using the DAO. If successful, it navigates
     * back to the search view.
     */
    @FXML
    protected void addItemToList() {
        try {
            // 1. Retrieve data from the form
            String productName = productNameTextField.getText();
            String productType = productTypeTextField.getText();
            String productDescription = productDescriptionTextField.getText();
            String productLocation = productLocationTextField.getText();
            String productCategory = "mock category";
            double productValue = 1;
            int productQuantity = Integer.parseInt(productQuantityTextField.getText());
            String productPurchaseDate = productPurchaseDateTextField.getText();
            boolean isInsured = insuredRadioButton.isSelected();
            double productPrice = Double.parseDouble(productPriceTextField.getText());

            // 2. Create a new Item object
            Item newItem = new Item(productName, productPurchaseDate, productPrice, productQuantity,
                    productDescription, productCategory, productType, productLocation, isInsured);

            // 3. Save the item using DAO
            itemDao.addItem(newItem);

            System.out.println("Item added successfully");
            // after item added successfully, navigate back to search page
            Stage stage = (Stage)addItemsButton.getScene().getWindow(); // Get current stage
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml")); // Load the next FXML
            Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT); // Set scene dimensions
            stage.setScene(scene); // Set the new scene
            stage.show(); // Show the updated stage


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding item: " + e.getMessage());
        }
    }

}
