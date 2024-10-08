package com.example.cab302simplestock.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public class User {
    int uID;
    String firstName;
    String lastName;
    String email;
    String password; // Only stores the hashed password, security?

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        //this.password = password; // TODO: Create a hashed value for the password for better security.
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
        try {
            MessageDigest hashFunc = MessageDigest.getInstance("sha256");
            byte[] encodedhash = hashFunc.digest(password.getBytes(StandardCharsets.UTF_8));
            System.out.println(Base64.getEncoder().encodeToString(encodedhash));
            String stringHashed = Base64.getEncoder().encodeToString(encodedhash);
            return Objects.equals(this.password, stringHashed);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHashedPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest hashFunc = MessageDigest.getInstance("sha256");
            byte[] encodedhash = hashFunc.digest(password.getBytes(StandardCharsets.UTF_8));
            this.password = Base64.getEncoder().encodeToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setID(int id) {
        this.uID = id;
    }
    public int getID()
    {
        return uID;
    }
    /*
    username
    password(hashed)
    groups
    adminLevel
     */
}
