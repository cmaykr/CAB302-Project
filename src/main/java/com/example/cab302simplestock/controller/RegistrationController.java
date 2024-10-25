package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import com.example.cab302simplestock.model.User;
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
import com.example.cab302simplestock.model.SqliteDAOs.SqliteUserDAO;

/**
 * Controller class for handling the user registration functionality.
 */
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

    /**
     * Constructor for RegistrationController.
     * Initializes the user DAO.
     */
    public RegistrationController() {
        userDAO = new SqliteUserDAO();
    }

    /**
     * Handles the creation of a new account.
     *
     * @param actionEvent The action event triggered by the user.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    private void onCreateAccount(ActionEvent actionEvent) throws IOException {
        if (!validateInput()) {
            return;
        }

        // Check if the email already exists
        if (userDAO.getUserByEmail(email.getText()) != null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Email already exists.");
            return;
        }

        User newUser = new User(firstName.getText(), lastName.getText(), email.getText(), password.getText(), securityQuestion.getText(), securityAnswer.getText());
        userDAO.addUser(newUser);

        List<User> users = userDAO.getAllUsers();
        System.out.println(users.get(users.size() - 1).getFirstName());

        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Validates the user input.
     *
     * @return true if the input is valid, false otherwise.
     */
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

    /**
     * Checks if the given email is in a valid format.
     *
     * @param email The email to check.
     * @return true if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
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
