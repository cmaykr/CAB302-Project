package com.example.cab302simplestock.model;

/**
 * Simple model class for representing a viewUser with the group ID its in and the user ID it represents in the group.
 */
public class ViewUser {
    private int viewUserID;
    private int groupID;
    private int userID;

    /**
     * Constructs a ViewUser with a group ID and user ID.
     * @param groupID The ID of the group its in.
     * @param userID The ID of the user it represents in the group.
     */
    public ViewUser(int groupID, int userID) {
        this.groupID = groupID;
        this.userID = userID;
    }

    /**
     * Gets the ID of ViewUser.
     * @return The ViewUser ID:
     */
    public int getID() {
        return viewUserID;
    }

    /**
     * Sets a new ID for the ViewUser.
     * @param newID The new ID, must be positive, >0.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setID(int newID) throws IllegalArgumentException {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("ViewUser ID cannot be negative, must be positive, >0.");
        }
        this.viewUserID = newID;
    }

    /**
     * Gets the ID of the user.
     * @return The user ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets a new ID to represent another user in the group.
     * @param newUserID The new user ID, must be positive, >0.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setUserID(int newUserID) throws IllegalArgumentException {
        if (newUserID <= 0)
        {
            throw new IllegalArgumentException("ViewUser user ID cannot be negative, must be positive, >0.");
        }
        userID = newUserID;
    }

    /**
     * Sets a new ID to represent another group
     * @param newID The new group ID, must be positive, >0.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setGroupID(int newID) throws IllegalArgumentException{
        if (newID <= 0)
        {
            throw new IllegalArgumentException("ViewUser group ID cannot be negative, must be positive, >0.");
        }

        groupID = newID;
    }

    /**
     * Gets the ID of the group.
     * @return The group ID.
     */
    public int getGroupID() {
        return groupID;
    }
}
