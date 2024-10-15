package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ForgottenpassController {

    @FXML
    private Label securityQuestionLabel;

    @FXML
    private TextField securityAnswer;

    private User user;

    public void initialize(User user) {
        this.user = user;
        securityQuestionLabel.setText(user.getSecurityQuestion());
    }

    @FXML
    private void onSubmitAnswer() {
        String answer = securityAnswer.getText();

        if (user.getSecurityAnswer().equals(answer)) {
            showAlert(Alert.AlertType.INFORMATION, "Answer Correct", "You can now reset your password.");
            // Redirect to password reset page
        } else {
            showAlert(Alert.AlertType.ERROR, "Answer Incorrect", "The answer you provided is incorrect.");
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