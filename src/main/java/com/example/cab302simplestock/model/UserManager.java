package com.example.cab302simplestock.model;

public class UserManager {
    private static UserManager instance;
    private User loggedInUser = null;

    private UserManager() { }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();

        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User newUser) {
        if (loggedInUser != null)
        {
            throw new IllegalStateException("A user is already logged in!");
        }
        loggedInUser = newUser;
    }

    public void logOutUser() {
        loggedInUser = null;
    }
}
