package com.example.cab302simplestock.model;

/**
 * Singleton class used to keep track of the current logged-in user.
 */
public class UserManager {
    private static UserManager instance;
    private User loggedInUser = null;

    private UserManager() { }

    /**
     * Gets the singleton instance of the UserManager, if the instance doesn't exist a new instance is created.
     * @return The instance of UserManager.
     */
    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();

        return instance;
    }

    /**
     * Gets the current logged-in user for the application.
     * @return The logged-in user as User type.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Sets a new logged-in user as a User type and stores all user information.
     * @param newUser The new user to be logged-in.
     * @throws IllegalStateException Throws if the last logged-in user has not logged-out.
     */
    public void setLoggedInUser(User newUser) throws IllegalStateException {
        if (loggedInUser != null)
        {
            throw new IllegalStateException("A user is already logged in!");
        }
        loggedInUser = newUser;
    }

    /**
     * Logs out the logged-in user.
     */
    public void logOutUser() {
        loggedInUser = null;
    }
}
