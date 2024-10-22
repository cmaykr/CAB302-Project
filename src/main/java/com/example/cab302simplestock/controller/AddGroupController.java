package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteCategoryDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteGroupDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteUserDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteViewUserDAO;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Controller class for adding a new group in the system.
 * Handle user interactions related to the group creation process.
 */
public class AddGroupController {
    /**
     * Text field for entering the group name.
     */
    @FXML
    private TextField groupNameField;
    /**
     * Group manager for interacting with group-related database operations.
     */
    private GroupManager groupManager;
    /**
     * Initialises the controller and sets up the group DAO for database access.
     * The DAO implementation used is {@code SqliteGroupDAO}.
     */
    public AddGroupController() {
        //viewUserDAO = new SqliteViewUserDAO();
        SqliteGroupDAO groupDao = new SqliteGroupDAO();
        CategoryManager categoryManager = new CategoryManager(new SqliteCategoryDAO());
        UserManager userManager = new UserManager(new SqliteUserDAO());
        ViewUserManager viewUserManager = new ViewUserManager(new SqliteViewUserDAO(), userManager);

        groupManager = new GroupManager(groupDao, categoryManager, viewUserManager);
    }

    /**
     * Initalises any required data or logic after the FXML components have been loaded.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize logic if needed
    }
    public int user_id = ActiveUserManager.getInstance().getLoggedInUser().getID();
    public int group_id;
    /**
     * Handles the action when the "ADD Group" button is clicked.
     * This method checks if the group name is provided, then adds the group to the database.
     * If successful, a success message is displayed, and the group name field is cleared.
     * Finally, the scene transitions back to the home page.
     *
     * @throws IOException if there is an error while loading the home page FXML file.
     */
    @FXML
    private void onAddGroupClick() throws IOException {
        String groupName = groupNameField.getText().trim();

        if (groupName.isEmpty()) {
            showAlert("Error", "Group name cannot be empty.");
            return;
        }

        // Assuming the owner ID is the logged-in user (hardcoded for now as 1)
        int ownerId = 1; // Replace this with dynamic user ID fetching in real implementation
        Group newGroup = new Group(groupName, ownerId);
        // Add the group to the database
        int group_id = groupManager.addGroup(newGroup);
        User user = ActiveUserManager.getInstance().getLoggedInUser();
        groupManager.addUser(user, group_id);
        Category category = new Category("Owned items", group_id);
        System.out.println(group_id);
        groupManager.addCategory(category);

        // Show success message
        showAlert("Success", "Group added successfully!");

        // Optionally clear the field after adding the group
        groupNameField.clear();
        Stage stage = (Stage) groupNameField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Handles the action when the "Back" button is clicked.
     * This method transitions the scene back to the home page.
     *
     * @throws IOException if there is an error while loading the home page FXML file.
     */
    @FXML
    private void onBackButtonClick() throws IOException {
        Stage stage = (Stage) groupNameField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Display an alert dialog with the given title and message.
     * @param title the title of the alter dialog
     * @param message  the message to be displayed in the alert dialog.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
