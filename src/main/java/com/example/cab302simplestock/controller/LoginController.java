package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.SimpleStock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public PasswordField password;
    @FXML
    public TextField username;
    @FXML
    public Button createAccountButton;

    public LoginController() {

    }

    public void onForgottenPassword() {
        // TODO: Implement method to reset password.
    }

    @FXML
    public void onSubmit() {
        System.out.println(username.getText());
        System.out.println(password.getText());

        // TODO: Implement method to login when submit button is pressed and get to homepage.
        mockLogin();
    }

    public void onCreateAccount() throws IOException {
        // TODO: Go to create an account page.
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("registration-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);
        stage.setScene(scene);
    }

    // mock login, get rid of later
    public void mockLogin() {
        // Load home page

        try {
            Stage stage = (Stage) username.getScene().getWindow(); // Get current stage
            FXMLLoader fxmlLoader = new FXMLLoader(SimpleStock.class.getResource("home-page.fxml")); // Load the FXML for the home page
            Scene homeScene = new Scene(fxmlLoader.load(), SimpleStock.WIDTH, SimpleStock.HEIGHT);

            // Show home page
            stage.setScene(homeScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
