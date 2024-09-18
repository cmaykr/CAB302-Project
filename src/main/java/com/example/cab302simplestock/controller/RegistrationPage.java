package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Objects;

import com.example.cab302simplestock.model.SqliteUserDAO;

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

    public void onCreateAccount(ActionEvent actionEvent) {
        SqliteUserDAO userDAO = new SqliteUserDAO();
        if (!Objects.equals(password.getText(), confirmPassword.getText()))
        {
            return;
        }
        User newUser = new User(firstName.getText(), lastName.getText(), email.getText(), password.getText());
        userDAO.addUser(newUser);

        List<User> users = userDAO.getAllUsers();
        System.out.println(users.get(0).getFirstName());
    }
}
