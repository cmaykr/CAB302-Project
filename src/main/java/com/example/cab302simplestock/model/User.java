package com.example.cab302simplestock.model;

import java.util.Objects;

public class User {
    int uID;
    String firstName;
    String lastName;
    String email;
    String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password; // TODO: Create a hashed value for the password for better security.
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword(String password) {
        // TODO: Add code to check if the hash from the inputted password is identical to the stored hashed password.
        return Objects.equals(this.password, password); // FIXME Temporary solution
    }

    public String getHashedPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setID(int id) {
        this.uID = id;
    }
    public int getID()
    {
        return uID;
    }
}
