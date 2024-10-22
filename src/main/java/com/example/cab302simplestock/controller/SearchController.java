package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteCategoryDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteTypeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
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
import javafx.util.Callback;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchController {
    //private IItemDAO itemDao;
    //private IGroupDAO groupDao;

    private ItemManager itemManager;
    private TypeManager typeManager;
    private Map<String, Integer> itemDisplayMap; // Map to store display text and item IDs

    public SearchController() {
        //itemDao = new SqliteItemDAO();
        IGroupDAO groupDao = new SqliteGroupDAO();
        itemDisplayMap = new HashMap<>();

        typeManager = new TypeManager(new SqliteTypeDAO());
        CategoryManager categoryManager = new CategoryManager(new SqliteCategoryDAO());
        itemManager = new ItemManager(new SqliteItemDAO(), typeManager, categoryManager);
    }

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

        // Set a custom cell factory for the ListView to add the clickable square
        itemsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    private HBox content;
                    private Text itemText;
                    private CheckBox checkBox;
                    private Region spacer; // A spacer to push the checkbox to the right

                    {
                        itemText = new Text();
                        checkBox = new CheckBox();  // This acts as the clickable square
                        spacer = new Region();
                        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS); // Spacer grows to fill space

                        content = new HBox(10); // Set spacing between the text and checkbox
                        content.getChildren().addAll(itemText, spacer, checkBox); // Text -> Spacer -> CheckBox

                        // Action handler for checkbox click
                        checkBox.setOnAction(event -> {
                            if (checkBox.isSelected()) {
                                // Handle checkbox checked
                                System.out.println("Item checked: " + itemText.getText());
                            } else {
                                // Handle checkbox unchecked
                                System.out.println("Item unchecked: " + itemText.getText());
                            }
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            itemText.setText(item);
                            setGraphic(content);
                        }
                    }
                };
            }
        });
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

        List<Item> items = itemManager.getAllItems();  // Get all items from the DAO

        // Loop through each item, add its name to the ListView and map it to its ID
        for (Item item : items) {
            String displayText = item.getName() + " - " + typeManager.getTypeByID(item.getTypeID()).getName();  // Example format
            itemsListView.getItems().add(displayText);
            itemDisplayMap.put(displayText, item.getItemID());  // Store the item ID in the map
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
        String productName = searchBar.getText(); // Assuming Username is the TextField for email

        if (productName == null || productName.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Search field cannot be empty.");
        }
    }
    private void filterItems(String searchText) {
        itemsListView.getItems().clear();
        itemDisplayMap.clear();

        List<Item> items = itemManager.getAllItems();

        for (Item item : items) {
            String displayText = item.getName() + " - " + item.getCategoryName(); // Example format
            // Check if the display text contains the search text
            if (displayText.toLowerCase().contains(searchText)) {
                itemsListView.getItems().add(displayText); // Add matching items to the ListView
                itemDisplayMap.put(displayText, item.getItemID()); // Store the item ID in the map
            }
        }

        if (itemsListView.getItems().isEmpty()) {
            // If no items found, show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No items found matching your search.", ButtonType.OK);
            alert.showAndWait();
        }
    }


}
