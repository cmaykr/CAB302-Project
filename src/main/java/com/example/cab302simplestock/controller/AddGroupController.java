package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteGroupDAO;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class AddGroupController {

    @FXML
    private TextField groupNameField;

    private IGroupDAO groupDao;

    public AddGroupController() {
        groupDao = new SqliteGroupDAO();
    }

    @FXML
    private void initialize() {
        // Initialize logic if needed
    }

    // Handler for Add Group button
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
        groupDao.addGroup(newGroup);

        // Show success message
        showAlert("Success", "Group added successfully!");

        // Optionally clear the field after adding the group
        groupNameField.clear();
        Stage stage = (Stage) groupNameField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    // Handler for Back button
    @FXML
    private void onBackButtonClick() throws IOException {
        Stage stage = (Stage) groupNameField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    // Utility function to show an alert message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
