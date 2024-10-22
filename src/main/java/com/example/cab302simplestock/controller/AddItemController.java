package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.SqliteDAOs.*;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.File;
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
    //private IItemDAO itemDao;
    private ItemManager itemManager;
    private TypeManager typeManager;

    /**
     * Constructgor for the AddItemContorller.
     * Initialises the DAO implementation for interacting with the database.
     */
    public AddItemController() {
        typeManager = new TypeManager(new SqliteTypeDAO());
        itemManager = new ItemManager(new SqliteItemDAO(), typeManager);
    }

    // Link to the FXML fields
    @FXML
    private ImageView plusIcon;
    @FXML
    private StackPane imageUploadPane;
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
    private ToggleGroup insuredToggleGroup;
    @FXML
    private TextField productPriceTextField;
    @FXML
    private Button addItemsButton;
    @FXML
    private Button ViewYourList;
    @FXML
    public void initialize() {
        insuredToggleGroup = new ToggleGroup();
        insuredRadioButton.setToggleGroup(insuredToggleGroup);
        notInsuredRadioButton.setToggleGroup(insuredToggleGroup);
    }
    @FXML
    protected void ViewListItem() throws IOException {
        Stage stage = (Stage) ViewYourList.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
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
            itemManager.addItem(newItem);

            // 4. Show success message
            showAlert("Success", "Item added successfully", Alert.AlertType.INFORMATION);

            // 5. Navigate back to search page
            Stage stage = (Stage) addItemsButton.getScene().getWindow(); // Get current stage
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml")); // Load the next FXML
            Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT); // Set scene dimensions
            stage.setScene(scene); // Set the new scene
            stage.show(); // Show the updated stage

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Error adding item: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleImageUpload(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Set extension filter to allow only image files
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Show open file dialog
        File file = fileChooser.showOpenDialog(imageUploadPane.getScene().getWindow());

        // If an image is selected, set it in the ImageView
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            plusIcon.setImage(image);
        } else {
            // Show an alert if no file was selected
            showAlert(Alert.AlertType.ERROR, "Image Upload Failed", "No image file selected.");
        }
    }


}
