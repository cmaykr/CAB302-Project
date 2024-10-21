package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;
import com.example.cab302simplestock.model.Item;
import com.example.cab302simplestock.model.SqliteDAOs.SqliteUserDAO;
import com.example.cab302simplestock.model.User;
import com.example.cab302simplestock.model.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsController {
    private IUserDAO userDAO;
    private Map<String, Integer> userDisplayMap;
    private User currentUser;

    public AccountsController() {
        userDAO = new SqliteUserDAO();
        userDisplayMap = new HashMap<>();
    }

    @FXML
    private Button updateButton;

    @FXML
    private TextField userFirstNameTextField;

    @FXML
    private TextField userLastNameTextField;

    @FXML
    private TextField userEmailTextField;

    @FXML
    public void initialize() {
        setupUserSelection();
    }

    private User setupUserSelection() {
        currentUser = UserManager.getInstance().getLoggedInUser();
        return currentUser;
    }


    @FXML
    protected void updateUser() throws IOException {
        // Create a new user object with the updated values from the fields
        if (currentUser != null) {
            currentUser.setFirstName(userFirstNameTextField.getText());
            currentUser.setLastName(userLastNameTextField.getText());
            currentUser.setEmail(userEmailTextField.getText());
            userFirstNameTextField.setText("");
            userLastNameTextField.setText("");
            userEmailTextField.setText("");

            // Update the item in the database
            userDAO.updateUser(currentUser);

        }
    }
}
