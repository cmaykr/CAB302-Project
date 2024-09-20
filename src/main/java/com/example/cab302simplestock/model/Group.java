package com.example.cab302simplestock.model;

public class Group {
    String groupName;
    int ownerID;

    public Group(String groupName, User groupOwner)
    {
        this.groupName = groupName;
        this.ownerID = groupOwner.getID();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwner(User newOwner) {
        this.ownerID = newOwner.getID();
    }
}
