package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class HomePageController {

    @FXML
    private VBox groupButtonBox;

    @FXML
    private Text welcomeText;

    @FXML
    private Button addGroup;

    // mock username
    private String username = "JohnDoe"; // This can be passed or loaded dynamically

    // mock inventories
    private List<String> groupList = List.of("Home Group", "Work Group", "Future Buys");

    @FXML
    public void initialize() {
        // Set the welcome text with the username
        welcomeText.setText("Welcome! " + username);
        // Dynamically create buttons for each group category
        for (String group : groupList) {
            Button groupButton = new Button(group);
            groupButton.setOnAction(event -> {
                try {
                    System.out.println(group);
                    handleGroupClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            groupButton.setPrefWidth(200.0); // Set button size as needed
            groupButtonBox.getChildren().add(groupButton); // Add button to VBox
        }
    }

    // Handle click on group buttons
    private void handleGroupClick() throws IOException {
        System.out.println("Clicked on group");

        // Load the new view
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        // Get the controller of the new view
        // ViewGroupController controller = fxmlLoader.getController();
        // Pass the group name to the new view
        // controller.setGroupName(groupName);
        // Set the new scene
        Stage stage = (Stage) addGroup.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    protected void onAddGroupClick() throws IOException {
        Stage stage = (Stage) addGroup.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

}


