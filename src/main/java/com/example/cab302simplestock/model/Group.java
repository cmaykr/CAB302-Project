package com.example.cab302simplestock.model;

public class Group {
    String groupName;
    String ownerID;

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

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwner(User newOwner) {
        this.ownerID = newOwner.getID();
    }
}
