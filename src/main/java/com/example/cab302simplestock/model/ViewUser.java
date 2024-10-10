package com.example.cab302simplestock.model;

public class ViewUser {
    int viewUserID;
    int groupID;
    int userID;

    public ViewUser(int groupID, int userID) {
        this.groupID = groupID;
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

    public void setGroupID(int newID) {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("ViewUser group ID cannot be negative, must be positive, >0.");
        }

        groupID = newID;
    }

    public int getGroupID() {
        return groupID;
    }
}
