package com.example.cab302simplestock.model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
    private String securityQuestion;
    private String securityAnswer;

    // Constructor
    public User(String firstName, String lastName, String email, String hashedPassword, String securityQuestion, String securityAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    // Getters and setters
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
}
