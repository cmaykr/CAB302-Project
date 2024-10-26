package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteCategoryDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteTypeDAO;
import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.GroupManager;
import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteCategoryDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import com.example.cab302simplestock.SimpleStock;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteGroupDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteItemDAO;
import com.example.cab302simplestock.model.Item;

import java.io.IOException;
import java.util.List;


public class SearchController {
    //private IItemDAO itemDao;
    //private IGroupDAO groupDao;

    private ItemManager itemManager;
    private TypeManager typeManager;
    private CategoryManager categoryManager;
    private Map<String, Integer> itemDisplayMap; // Map to store display text and item IDs

    public SearchController() {
        //itemDao = new SqliteItemDAO();
        IGroupDAO groupDao = new SqliteGroupDAO();
        itemDisplayMap = new HashMap<>();

        typeManager = new TypeManager(new SqliteTypeDAO());
        categoryManager = new CategoryManager(new SqliteCategoryDAO());
        itemManager = new ItemManager(new SqliteItemDAO(), typeManager, categoryManager);
    }

    @FXML
    private Label totalValueLabel;
    @FXML
    private Label totalCountLabel;
    @FXML
    private Label groupLabel;
    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;
    @FXML
    private ListView<String> itemsListView;
    @FXML
    private Button leaveGroupButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Button AddItem;


    @FXML
    protected void addItemsButton() throws IOException {
        Stage stage = (Stage) addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("add-item-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }


    @FXML
    public void initialize() {
        loadItems();
        setupItemSelection();
    }

    public void setGroupName(String groupName) {
        groupLabel.setText(groupName);
    }



    private void setupItemSelection() {
        itemsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Retrieve the item ID from the display map
                int itemId = itemDisplayMap.get(newValue);
                try {
                    goToItemView(itemId);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void goToItemView(int itemId) throws IOException {
        Stage stage = (Stage) itemsListView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("list-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);

        listViewController controller = fxmlLoader.getController();
        controller.loadItemById(itemId);  // Pass the item ID to the listViewController
        stage.setScene(scene);
    }
    private void loadItems() {
        itemsListView.getItems().clear();
        itemDisplayMap.clear();  // Clear the map to avoid old data

        double totalValue = 0.0;
        int totalCount = 0;

        //List<Item> items = itemManager.getAllItems();  // Get all items from the DAO
        // Get all items from the DAO
        List<Item> items = itemManager.getAllItems();

        // Get the selected group from GroupManager
        Group currentGroup = ActiveGroupManager.getInstance().getActiveGroup();

        if (currentGroup == null) {
            // If no group is selected, show an alert and return early
            showAlert(Alert.AlertType.WARNING, "No Group Selected", "Please select a group first.");
            return;
        }

        // Fetch all categories from the database
        System.out.println("getting categories"); // there are no categories...
        List<Category> allCategories = categoryManager.getAllCategories(); // Assuming groupDao is using SqliteCategoryDAO

        // Filter categories based on the current group's ID
        List<Integer> categoryIds = new ArrayList<>();
        for (Category category : allCategories) {

            System.out.println(category.getCategoryID());
            if (category.getGroupID() == currentGroup.getGroupID()) {
                categoryIds.add(category.getCategoryID());
            }
        }

        // Now filter the items based on the category IDs
        for (Item item : items) {
            if (categoryIds.contains(item.getCategoryID())) {
                // Only add items if their categoryID matches one in the selected group
                String displayText = item.getName() + " - " + typeManager.getTypeByID(item.getTypeID()).getName();
                itemsListView.getItems().add(displayText);
                itemDisplayMap.put(displayText, item.getItemID());  // Store the item ID in the map
                totalValue += item.getPurchasePrice();
                totalCount++;
                totalValueLabel.setText("Total Value: $" + String.format("%.2f", totalValue));
                totalCountLabel.setText("Total Count: " + totalCount);
            }
        }

        if (itemsListView.getItems().isEmpty()) {
            // If no items found for the selected group, show an alert
            showAlert(Alert.AlertType.INFORMATION, "No Items Found", "No items found for the selected group.");
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // Optional: Remove header
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    protected void searchButtonClick() {
        String searchText = searchBar.getText(); // Assuming Username is the TextField for email

        if (searchText == null || searchText.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Search field cannot be empty.");
        } else {
            filterItems(searchText.toLowerCase());
        }
    }
    private void filterItems(String searchText) {
        itemsListView.getItems().clear();
        itemDisplayMap.clear();

        List<Item> items = itemManager.getAllItems();
        Group currentGroup = ActiveGroupManager.getInstance().getActiveGroup();

        if (currentGroup == null) {
            showAlert(Alert.AlertType.WARNING, "No Group Selected", "Please select a group first.");
            return;
        }

        // Get categories matching the current group ID
        List<Category> allCategories = categoryManager.getAllCategories();
        List<Integer> categoryIds = new ArrayList<>();
        for (Category category : allCategories) {
            if (category.getGroupID() == currentGroup.getGroupID()) {
                categoryIds.add(category.getCategoryID());
            }
        }

        // Filter and display items with a name or ID that contains the search text
        for (Item item : items) {
            if (categoryIds.contains(item.getCategoryID())) {
                String displayText = item.getName() + " - " + item.getCategoryID();
                if (displayText.toLowerCase().contains(searchText)) {  // Case-insensitive match
                    itemsListView.getItems().add(displayText);
                    itemDisplayMap.put(displayText, item.getItemID());
                }
            }
        }

        if (itemsListView.getItems().isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "No Items Found", "No items found matching your search.");
        }
    }



}
