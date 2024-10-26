package com.example.cab302simplestock.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

/**
 * A simple model class for representing a User with a first name, last name, email, hashed password, security question and a security answer.
 */
public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String securityQuestion;
    private String securityAnswer;

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
        this.securityQuestion = "";
        this.securityAnswer = "";
    }

    /**
     * Constructs a user with its first name, last name, email, hashed password, security question and a security answer.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email associated to the user.
     * @param password The plaintext password of the user.
     * @param securityQuestion The security question for the user.
     * @param securityAnswer The corresponding answer to the security question.
     */
    public User(String firstName, String lastName, String email, String password, String securityQuestion, String securityAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    /**
     * Gets the first name of the user.
     * @return The users first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets a new first name of the user.
     * @param firstName The new first name of the user, can be any non-empty value.
     * @throws IllegalArgumentException If the firstName parameter is empty.
     */
    public void setFirstName(String firstName) throws IllegalArgumentException {
        if (firstName.isEmpty())
        {
            throw new IllegalArgumentException("User first name cannot be empty.");
        }
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * @return The users last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets a new last name of the user.
     * @param lastName The new last name of the user, can be any non-empty value.
     * @throws IllegalArgumentException If the lastName parameter is empty.
     */
    public void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName.isEmpty())
        {
            throw new IllegalArgumentException("User last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    /**
     * Gets the email associated with the user.
     * @return The users email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets a new email of the user.
     * @param email The new last name of the user, can be any non-empty and email valid format.
     * @throws IllegalArgumentException If the email parameter is empty or the format is invalid.
     */
    public void setEmail(String email) throws IllegalArgumentException {
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

    /**
     * Checks if the inputted plaintext password is identical to the stored password.
     * @param password The plaintext password that should be checked with the users password.
     * @return True if the passwords match, false otherwise.
     */
    public boolean checkPassword(String password) {
        try {
            MessageDigest hashFunc = MessageDigest.getInstance("sha256");
            byte[] encodedhash = hashFunc.digest(password.getBytes(StandardCharsets.UTF_8));
            String stringHashed = Base64.getEncoder().encodeToString(encodedhash);
            return Objects.equals(this.password, stringHashed);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the users hashed password.
     * @return The hashed password.
     */
    public String getHashedPassword() {
        return password;
    }

    /**
     * Sets a new hashed password.
     * @param hashedPassword The hashed password, needs to be hashed with SHA256.
     * @throws IllegalArgumentException If the hashedPassword parameter is empty.
     */
    public void setHashedPassword(String hashedPassword) throws IllegalArgumentException {
        if (hashedPassword.isEmpty())
        {
            throw new IllegalArgumentException(("User hashed password cannot be empty."));
        }

        this.password = hashedPassword;
    }

    /**
     * Sets a new password and stores the hashed value of it.
     * @param password The plaintext password, must be any non-empty value.
     * @throws IllegalArgumentException If the password parameter is empty.
     */
    public void setPassword(String password) throws IllegalArgumentException {
        if (password.isEmpty())
        {
            throw new IllegalArgumentException("User password cannot be empty.");
        }
        try {
            MessageDigest hashFunc = MessageDigest.getInstance("sha256");
            byte[] encodedhash = hashFunc.digest(password.getBytes(StandardCharsets.UTF_8));
            this.password = Base64.getEncoder().encodeToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets a new ID for the user.
     * @param id The new ID, must be positive, >0.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setID(int id) throws IllegalArgumentException {
        if (id <= 0)
        {
            throw new IllegalArgumentException("User ID cannot be negative, must be a positive value, >0.");
        }
        this.userID = id;
    }

    /**
     * Gets the users ID.
     * @return The users ID.
     */
    public int getID()
    {
        return userID;
    }

    /**
     * Gets the security question for the user.
     * @return The security question.
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * Sets a new security question for the user.
     * @param securityQuestion The new security question, must be any non-empty value.
     * @throws IllegalArgumentException If the securityQuestion parameter is empty.
     */
    public void setSecurityQuestion(String securityQuestion) throws IllegalArgumentException {
        if (securityQuestion.isEmpty())
        {
            throw new IllegalArgumentException("User security question cannot be empty.");
        }
        this.securityQuestion = securityQuestion;
    }

    /**
     * Sets a new security answer for the user.
     * @param securityAnswer The new security answer, must be any non-empty value.
     * @throws IllegalArgumentException If the securityAnswer parameter is empty.
     */
    public void setSecurityAnswer(String securityAnswer) throws IllegalArgumentException {
        if (securityAnswer.isEmpty())
        {
            throw new IllegalArgumentException("User security answer cannot be empty.");
        }
        this.securityAnswer = securityAnswer;
    }

    /**
     * Gets the security answer for the user.
     * @return The security answer.
     */
    public String getSecurityAnswer() {
        return securityAnswer;
    }
}
