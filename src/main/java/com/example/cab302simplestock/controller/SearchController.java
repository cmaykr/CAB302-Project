package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteGroupDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteItemDAO;
import com.example.cab302simplestock.model.Item;

import javafx.scene.control.Label; // Import Label
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class SearchController {
    private IItemDAO itemDao;
    private IGroupDAO groupDao;
    private Map<String, Integer> itemDisplayMap; // Map to store display text and item IDs

    public SearchController() {
        itemDao = new SqliteItemDAO();
        groupDao = new SqliteGroupDAO();
        itemDisplayMap = new HashMap<>();
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
    protected void addItemsButton() throws IOException {
        Stage stage = (Stage) addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("add-item-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void backButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void logoutClicked() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }


    @FXML
    protected void leaveGroupButton() throws IOException {
        String groupName = groupLabel.getText();  // Get the group name from the label

        if (groupName != null && !groupName.isEmpty()) {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Leave Group");
            alert.setHeaderText("Are you sure you want to leave the group?");
            alert.setContentText("Group: " + groupName);

            // Wait for the user to click OK or Cancel
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK, delete the group
                Group group = new Group(groupName, 0);  // Assuming ownerID is not required here
                groupDao.deleteGroup(group);

                // Navigate back to the login page after leaving the group
                Stage stage = (Stage) leaveGroupButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
                stage.setScene(scene);
            } else {
                // User clicked Cancel or closed the dialog, do nothing
            }
        }
    }



    @FXML
    public void initialize() {
        loadItems();
        setupItemSelection();
    }

    public void setGroupName(String groupName) {
        groupLabel.setText(groupName);  // Display selected group name
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

        List<Item> items = itemDao.getAllItems();  // Get all items from the DAO

        // Loop through each item, add its name to the ListView and map it to its ID
        for (Item item : items) {
            String displayText = item.getName() + " - " + item.getCategoryName();  // Example format
            itemsListView.getItems().add(displayText);
            itemDisplayMap.put(displayText, item.getID());  // Store the item ID in the map
        }
    }
}
