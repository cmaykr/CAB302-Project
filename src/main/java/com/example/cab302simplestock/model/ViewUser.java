package com.example.cab302simplestock.model;

public class ViewUser {
    int viewUserID;
    String groupName;
    int userID;

    public ViewUser(Group group, User user) {
        this.groupName = group.getGroupName();
        this.userID = user.getID();
    }

    public ViewUser(String groupName, int userID) {
        this.groupName = groupName;
        this.userID = userID;
    }

    public int getID() {
        return viewUserID;
    }

    public void setID(int newID) {
        this.viewUserID = newID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int newUserID) {
        userID = newUserID;
    }
}
