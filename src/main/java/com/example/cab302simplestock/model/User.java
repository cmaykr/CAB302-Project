package com.example.cab302simplestock.model;

import java.util.Objects;

public class User {
    int userID;
    String firstName;
    String lastName;
    String email;
    String password;
    String securityQuestion;
    String securityAnswer;

    /**
     * DEPRECATED, use other User constructor.
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     */
    public User(String firstName, String lastName, String email, String password) {
        System.out.println("DEPRECATED User constructor usage, use the other constructor.");
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password; // TODO: Create a hashed value for the password for better security.
    }

    public User(String firstName, String lastName, String email, String password, String securityQuestion, String securityAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password; // TODO: Create a hashed value for the password for better security.
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty())
        {
            throw new IllegalArgumentException("User first name cannot be empty.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty())
        {
            throw new IllegalArgumentException("User last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isEmpty())
        {
            throw new IllegalArgumentException("User email cannot be empty.");
        }
        if (!email.contains("@"))
        {
            throw new IllegalArgumentException("User email format invalid, use format: name@domain.");
        }
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
        if (password.isEmpty())
        {
            throw new IllegalArgumentException("User password cannot be empty.");
        }
        this.password = password;
    }

    public void setID(int id) {
        if (id <= 0)
        {
            throw new IllegalArgumentException("User ID cannot be negative, must be a positive value, >0.");
        }
        this.userID = id;
    }

    public int getID()
    {
        return userID;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        if (securityQuestion.isEmpty())
        {
            throw new IllegalArgumentException("User security question cannot be empty.");
        }
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        if (securityAnswer.isEmpty())
        {
            throw new IllegalArgumentException("User security answer cannot be empty.");
        }
        this.securityAnswer = securityAnswer;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }
}
