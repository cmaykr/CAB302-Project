package com.example.cab302simplestock.model;

public class ViewUser {
    int viewUserID;
    String groupName;
    int userID;

    public ViewUser(String groupName, int userID) {
        this.groupName = groupName;
        this.userID = userID;
    }

    public int getID() {
        return viewUserID;
    }

    public void setID(int newID) {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("ViewUser ID cannot be negative, must be positive, >0.");
        }
        this.viewUserID = newID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        if (groupName.isEmpty())
        {
            throw new IllegalArgumentException("ViewUser group name cannot be empty.");
        }
        this.groupName = groupName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int newUserID) {
        if (newUserID <= 0)
        {
            throw new IllegalArgumentException("ViewUser user ID cannot be negative, must be positive, >0.");
        }
        userID = newUserID;
    }
}
