package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.GroupManager;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteGroupDAO;
import com.example.cab302simplestock.model.User;
import com.example.cab302simplestock.model.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomePageController {

    @FXML
    private VBox groupButtonBox;

    @FXML
    private Text welcomeText;

    @FXML
    private Button addGroup;

    private SqliteGroupDAO groupDAO;
    private User loggedInUser = UserManager.getInstance().getLoggedInUser();
    private String username = loggedInUser.getFirstName();  // Example username, replace with the actual logged-in user data
    public HomePageController() {
        groupDAO = new SqliteGroupDAO();  // Initialize the Group DAO
    }

    @FXML
    public void initialize() {
        welcomeText.setText("Welcome! " + username);
        loadGroups();
    }

    // Load the groups from the database and populate the VBox with buttons
    private void loadGroups() {
        List<Group> groupList = groupDAO.getAllGroups();  // Fetch groups from the database

        for (Group group : groupList) {
            // Create an HBox to hold both the group button and the leave button
            HBox groupBox = new HBox(10); // 10 px spacing between elements

            // Create the group button
            Button groupButton = new Button(group.getGroupName());
            groupButton.setOnAction(event -> {
                try {
                    handleGroupClick(group);  // Pass the selected group name
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            groupButton.setPrefWidth(150.0); // Set width of the group button
            groupButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;"); // change colour of leave group button
            // Create the "Leave Group" button
            Button leaveGroupButton = new Button("Leave Group");
            leaveGroupButton.setOnAction(event -> {
                handleLeaveGroupClick(group);  // Handle leaving the group
            });
            leaveGroupButton.setPrefWidth(100.0); // Set width of the leave button
            leaveGroupButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;"); // change colour of leave group button
            // Add both buttons to the HBox
            groupBox.getChildren().addAll(groupButton, leaveGroupButton);

            // Add the HBox to the VBox
            groupButtonBox.getChildren().add(groupBox);
        }
    }

    // Handle the action of clicking a group button (load the group details or search view)
    private void handleGroupClick(Group group) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("add-item-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);

        // Pass the group name to SearchController
        //SearchController controller = fxmlLoader.getController();
        AddItemController controller = fxmlLoader.getController();
        //controller.setGroupName(groupName);  // Set the selected group name replace with group singleton
        GroupManager.setSelectedGroup(group);

        Stage stage = (Stage) addGroup.getScene().getWindow();
        stage.setScene(scene);
    }

    // Handle the action of clicking the "Leave Group" button (delete the group)
    private void handleLeaveGroupClick(Group group) {
        // Remove the group from the database
        groupDAO.deleteGroup(group);
        GroupManager.deselectGroup();
        // Refresh the group list in the UI
        groupButtonBox.getChildren().clear();
        loadGroups();
    }

    @FXML
    protected void onAddGroupClick() throws IOException {
        Stage stage = (Stage) addGroup.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("add-group.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }



}
