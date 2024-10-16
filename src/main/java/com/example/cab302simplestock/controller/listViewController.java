package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.Item;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteItemDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for managing the product details in the list view screen of SimpleStock.
 * It allows users to view, update, or delete existing inventory items.
 */
public class listViewController {

    @FXML
    private Button goBack;

    @FXML
    private Button confirmButton; // Added confirm button

    @FXML
    private Button deleteButton; // Added delete button

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField productTypeTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField quantityTextField; // Assuming this is where you handle the item value

    @FXML
    private TextField purchaseDateTextField;

    @FXML
    private RadioButton insuredTextField;

    @FXML
    private TextField priceTextField;

    private IItemDAO itemDAO;
    private Item currentItem; // Store the current item for updates/deletes
    /**
     * Event handler for the "Go Back" button. Navigates back to the search view.
     *
     * @throws IOException if the FXML file for the search view cannot be loaded.
     */
    @FXML
    protected void goBack() throws IOException {
        Stage stage = (Stage) goBack.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }
    /**
     * Initializes the controller by setting up the DAO for database interaction.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        itemDAO = new SqliteItemDAO();  // Initialize the DAO
    }
    /**
     * Loads an item from the database by its ID and populates the input fields with the item's details.
     *
     * @param itemId the ID of the item to load.
     */
    public void loadItemById(int itemId) {
        loadItemFromDatabase(itemId);  // Load item by ID
    }
    /**
     * Retrieves the item from the database and populates the input fields if the item exists.
     *
     * @param itemId the ID of the item to load.
     */
    private void loadItemFromDatabase(int itemId) {
        List<Item> items = itemDAO.getAllItems();  // Get all items

        for (Item item : items) {
            if (item.getItemID() == itemId) {  // Check if the item's ID matches the one we want
                currentItem = item; // Store the current item
                System.out.println(currentItem.getItemID());
                populateFields(item);  // Populate the fields with the item's details
                break;  // Exit the loop once we find the item
            }
        }
    }

    /**
     * Populates the input fields with the data from the given item.
     *
     * @param item the item whose data to be displayed.
     */
    private void populateFields(Item item) {

        productNameTextField.setText(item.getName());
        productTypeTextField.setText(item.getTypeName());
        descriptionTextField.setText(item.getDescription());
        locationTextField.setText(item.getLocation());
        quantityTextField.setText(String.valueOf(item.getQuantity()));
        purchaseDateTextField.setText(item.getPurchaseDate());
        insuredTextField.setSelected(item.getInsured());
        priceTextField.setText(String.valueOf(item.getPurchasePrice()));

    }
    /**
     * Event handler for the "Confirm" button. Updates the currently viewed item with the data
     * from the input fields and saves the changes to the database.
     *
     * @throws IOException if there is an error navigating to the previous screen after the update.
     */
    @FXML
    protected void updateItem() throws IOException {
        //System.out.println(currentItem.getPurchaseDate());
        // Create a new item object with the updated values from the fields
        if (currentItem != null) {
            currentItem.setName(productNameTextField.getText());
            currentItem.setTypeName(productTypeTextField.getText());
            currentItem.setDescription(descriptionTextField.getText());
            currentItem.setLocation(locationTextField.getText());
            currentItem.setQuantity(Double.parseDouble(quantityTextField.getText()));
            currentItem.setPurchaseDate(purchaseDateTextField.getText());
            currentItem.setInsured(insuredTextField.isSelected());
            currentItem.setPurchasePrice(Double.parseDouble(priceTextField.getText()));

            // Update the item in the database
            itemDAO.updateItem(currentItem);
            //System.out.println(currentItem.getPurchaseDate());
            goBack();

        }
    }
    /**
     * Event handler for the "Delete" button. Deletes the currently viewed item from the database
     * and navigates back to the previous screen.
     *
     * @throws IOException if there is an error navigating to the previous screen after deletion.
     */
    @FXML
    protected void deleteItem() throws IOException {
        if (currentItem != null) {
            itemDAO.deleteItem(currentItem); // Delete the item from the database
            goBack(); // Go back to the previous screen after deletion
        }
    }
}
