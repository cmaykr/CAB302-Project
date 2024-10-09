package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.SqliteUserDAO;
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
    private void onSubmit() throws IOException {
        String email = username.getText();
        String pwd = password.getText();

        if (email.equals(ADMIN_EMAIL) && pwd.equals(ADMIN_PASSWORD)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, Admin!");
            loadHomePage();
        } else {
            User user = userDao.getUserByEmail(email);
            if (user != null && user.getHashedPassword().equals(pwd)) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + user.getFirstName() + "!");
                loadHomePage();
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password.");
            }
        }
    }

    @FXML
    private void onForgottenPassword() {
        showAlert(Alert.AlertType.INFORMATION, "Forgotten Password", "Please contact support to reset your password.");
    }

    @FXML
    private void onCreateAccount() {
        showAlert(Alert.AlertType.INFORMATION, "Create Account", "Account creation is not implemented yet.");
    }

    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302simplestock/view/home-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
