package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void onSubmit() {
        System.out.println(username.getText());
        System.out.println(password.getText());

        // TODO: Implement method to login when submit button is pressed and get to homepage.
    }

    public void onCreateAccount() {
        // TODO: Go to create an account page.
        //Stage stage = (Stage) createAccountButton.getScene().getWindow();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createAccountPage.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        //stage.setScene(scene);
    }
}
