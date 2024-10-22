package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.model.SqliteDAOs.SqliteUserDAO;
import com.example.cab302simplestock.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgottenpassController {

    @FXML
    private Label securityQuestionLabel;

    @FXML
    private TextField securityAnswer;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Label newPasswordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Button confirmButton;

    private User user;
    private SqliteUserDAO userDao;

    public void initialize(User user) {
        this.user = user;
        this.userDao = new SqliteUserDAO();
        securityQuestionLabel.setText(user.getSecurityQuestion());
        newPasswordLabel.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        newPassword.setVisible(false);
        confirmPassword.setVisible(false);
        confirmButton.setVisible(false);
    }

    @FXML
    private void onSubmitAnswer() {
        String answer = securityAnswer.getText();

        if (user.getSecurityAnswer().equals(answer)) {
            showAlert(Alert.AlertType.INFORMATION, "Answer Correct", "You can now reset your password.");
            newPasswordLabel.setVisible(true);
            confirmPasswordLabel.setVisible(true);
            newPassword.setVisible(true);
            confirmPassword.setVisible(true);
            confirmButton.setVisible(true);
        } else {
            showAlert(Alert.AlertType.ERROR, "Answer Incorrect", "The answer you provided is incorrect.");
        }
    }

    @FXML
    private void onResetPassword() {
        String newPass = newPassword.getText();
        String confirmPass = confirmPassword.getText();

        if (newPass.equals(confirmPass)) {
            user.setPassword(newPass); // This will hash the password
            userDao.updateUser(user); // Update the user in the database
            showAlert(Alert.AlertType.INFORMATION, "Password Reset", "Your password has been reset successfully.");
            // Return to login page
            returnToLogin();
        } else {
            showAlert(Alert.AlertType.ERROR, "Password Mismatch", "The passwords do not match.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void returnToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cab302simplestock/login-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the login page.");
        }
    }
}
