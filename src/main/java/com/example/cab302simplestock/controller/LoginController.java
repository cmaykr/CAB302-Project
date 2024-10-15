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

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private SqliteUserDAO userDao;

    // Hardcoded admin credentials
    private static final String ADMIN_EMAIL = "admin@example.com";
    private static final String ADMIN_PASSWORD = "admin123";

    public LoginController() {
        userDao = new SqliteUserDAO();
    }

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

    @FXML
    private void onForgottenPassword() {
        String email = username.getText();
        try {
            User user = userDao.getUserByEmail(email);
            if (user != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302simplestock/forgotten-pass.fxml"));
                Parent root = loader.load();

                com.example.cab302simplestock.controller.ForgottenpassController controller = loader.getController();
                controller.initialize(user);

                Stage stage = (Stage) username.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No user found with this email.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the security question page.");
        }
    }



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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
