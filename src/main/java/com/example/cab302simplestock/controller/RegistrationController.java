package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.User;
import com.example.cab302simplestock.model.SqliteUserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class RegistrationController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField securityQuestion;
    @FXML
    private TextField securityAnswer;
    @FXML
    private Button signUpButton;

    private SqliteUserDAO userDAO;

    public RegistrationController() {
        userDAO = new SqliteUserDAO();
    }

    @FXML
    private void onCreateAccount(ActionEvent actionEvent) throws IOException {
        if (!validateInput()) {
            return;
        }

        String hashedPassword = hashPassword(password.getText());
        User newUser = new User(firstName.getText(), lastName.getText(), email.getText(), hashedPassword, securityQuestion.getText(), securityAnswer.getText());
        userDAO.addUser(newUser);

        List<User> users = userDAO.getAllUsers();
        System.out.println(users.get(users.size() - 1).getFirstName());

        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    private boolean validateInput() {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() ||
                password.getText().isEmpty() || confirmPassword.getText().isEmpty() ||
                securityQuestion.getText().isEmpty() || securityAnswer.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled out.");
            return false;
        }

        if (!Objects.equals(password.getText(), confirmPassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return false;
        }

        if (!isValidEmail(email.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid email format.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private String hashPassword(String password) {
        // Implement your password hashing logic here
        return password; // Placeholder, replace with actual hashing
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
