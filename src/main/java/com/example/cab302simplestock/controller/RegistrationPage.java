package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.HelloApplication;
import com.example.cab302simplestock.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import com.example.cab302simplestock.model.SqliteUserDAO;
import javafx.stage.Stage;

public class RegistrationPage {
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    PasswordField confirmPassword;
    @FXML
    Button SignUpButton;

    public void onCreateAccount(ActionEvent actionEvent) throws IOException {
        SqliteUserDAO userDAO = new SqliteUserDAO();
        if (!Objects.equals(password.getText(), confirmPassword.getText()))
        {
            return;
        }
        User newUser = new User(firstName.getText(), lastName.getText(), email.getText(), password.getText());
        userDAO.addUser(newUser);

        List<User> users = userDAO.getAllUsers();
        System.out.println(users.get(users.size()-1).getFirstName());

        Stage stage = (Stage) SignUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
