package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.model.SqliteDAOs.SqliteUserDAO;
import com.example.cab302simplestock.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for handling the login functionality.
 */
public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private SqliteUserDAO userDao;

    // Hardcoded admin credentials
    private static final String ADMIN_EMAIL = "admin@example.com";
    private static final String ADMIN_PASSWORD = "admin123";

    /**
     * Constructor for LoginController.
     * Initializes the user DAO.
     */
    public LoginController() {
        userDao = new SqliteUserDAO();
    }

    /**
     * Handles the submission of the login form.
     */
    @FXML
    private void onSubmit() {
        String email = username.getText();
        String pwd = password.getText();

        try {
            if (email.equals(ADMIN_EMAIL) && pwd.equals(ADMIN_PASSWORD)) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, Admin!");
                loadHomePage();
            } else {
                User user = userDao.getUserByEmail(email);
                if (user != null && user.checkPassword(pwd)) {
                    showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + user.getFirstName() + "!");
                    loadHomePage();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred.");
        }
    }

    /**
     * Handles the forgotten password action.
     */
    @FXML
    private void onForgottenPassword() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302simplestock/forgotten-pass.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the security question page.");
        }
    }

    /**
     * Handles the create account action.
     */
    @FXML
    private void onCreateAccount() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302simplestock/registration-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the registration page.");
        }
    }

    /**
     * Loads the home page after successful login.
     */
    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302simplestock/home-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the home page.");
        }
    }

    /**
     * Displays an alert with the given type, title, and message.
     *
     * @param alertType The type of alert.
     * @param title     The title of the alert.
     * @param message   The message of the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
