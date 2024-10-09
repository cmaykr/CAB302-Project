package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteGroupDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private String username = "JohnDoe";  // Example username, replace with the actual logged-in user data

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
            Button groupButton = new Button(group.getGroupName());
            groupButton.setOnAction(event -> {
                try {
                    handleGroupClick(group.getGroupName());  // Pass the selected group name
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            groupButton.setPrefWidth(200.0);
            groupButtonBox.getChildren().add(groupButton);
        }
    }

    private void handleGroupClick(String groupName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);

        // Pass the group name to SearchController
        SearchController controller = fxmlLoader.getController();
        controller.setGroupName(groupName);  // Set the selected group name

        Stage stage = (Stage) addGroup.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    protected void onAddGroupClick() throws IOException {
        Stage stage = (Stage) addGroup.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("add-group.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }
}
