package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteItemDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    @FXML
    protected void backButtonClick() throws IOException{
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),SimpleStock.WIDTH,SimpleStock.HEIGHT);
        stage.setScene(scene);
    }
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait(); // Wait for the user to close the alert
    }
    private boolean validateForm() {
        if (productNameTextField.getText().isEmpty() ||
                productTypeTextField.getText().isEmpty() ||
                productDescriptionTextField.getText().isEmpty() ||
                productLocationTextField.getText().isEmpty() ||
                productQuantityTextField.getText().isEmpty() ||
                productPurchaseDateTextField.getText().isEmpty() ||
                productPriceTextField.getText().isEmpty()) {
            showAlert("Validation Error", "Please fill all fields", Alert.AlertType.WARNING);
            return false;
        }
        try {
            // Validate quantity
            Integer.parseInt(productQuantityTextField.getText());
            // Validate price
            Double.parseDouble(productPriceTextField.getText());
            // Validate date format (simple check, improve if necessary)
            LocalDate.parse(productPurchaseDateTextField.getText());
        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Please enter valid numbers for quantity and price.", AlertType.WARNING);
            return false;
        } catch (Exception e) {
            showAlert("Validation Error", "Please enter a valid date in YYYY-MM-DD format.", AlertType.WARNING);
            return false;
        }
        return true;
    }
    @FXML
    protected void addItemToList() {
        if (!validateForm()) {
            return; // Exit if validation fails
        }
        try {
            // 1. Retrieve data from the form
            String productName = productNameTextField.getText();
            String productType = productTypeTextField.getText();
            String productDescription = productDescriptionTextField.getText();
            String productLocation = productLocationTextField.getText();
            String productCategory = "mock category"; // Temporary category
            int productQuantity = Integer.parseInt(productQuantityTextField.getText());
            String productPurchaseDate = productPurchaseDateTextField.getText();
            boolean isInsured = insuredRadioButton.isSelected();
            double productPrice = Double.parseDouble(productPriceTextField.getText());

            // 2. Create a new Item object
            Item newItem = new Item(productName, productPurchaseDate, productPrice, productQuantity,
                    productDescription, productCategory, productType, productLocation, isInsured);

            // 3. Save the item using DAO
            itemDao.addItem(newItem);

            // 4. Show success message
            showAlert("Success", "Item added successfully", Alert.AlertType.INFORMATION);

            // 5. Navigate back to search page
            Stage stage = (Stage)addItemsButton.getScene().getWindow(); // Get current stage
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml")); // Load the next FXML
            Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT); // Set scene dimensions
            stage.setScene(scene); // Set the new scene
            stage.show(); // Show the updated stage

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Error adding item: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }



}
