package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.Item;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.ItemManager;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteItemDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteTypeDAO;
import com.example.cab302simplestock.model.Type;
import com.example.cab302simplestock.model.TypeManager;
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

    //private IItemDAO itemDAO;
    private Item currentItem; // Store the current item for updates/deletes
    private TypeManager typeManager;
    private ItemManager itemManager;
    /**
     * Event handler for the "Go Back" button. Navigates back to the search view.
     *
     * @throws IOException if the FXML file for the search view cannot be loaded.
     */
    @FXML
    protected void goBack() throws IOException {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
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
        //itemDAO = new SqliteItemDAO();  // Initialize the DAO
        typeManager = new TypeManager(new SqliteTypeDAO());
        itemManager = new ItemManager(new SqliteItemDAO(), typeManager);
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
        List<Item> items = itemManager.getAllItems();  // Get all items
        Item item = itemManager.findItemByID(itemId);
        currentItem = item;
        System.out.println(currentItem.getItemID());
        System.out.println(currentItem.getTypeID());
        populateFields(item);
    }

    /**
     * Populates the input fields with the data from the given item.
     *
     * @param item the item whose data to be displayed.
     */
    private void populateFields(Item item) {

        productNameTextField.setText(item.getName());
        productTypeTextField.setText(typeManager.getTypeByID(item.getTypeID()).getName());
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
            itemManager.updateItem(currentItem);
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
            itemManager.deleteItem(currentItem); // Delete the item from the database
            goBack(); // Go back to the previous screen after deletion
        }
    }
}
