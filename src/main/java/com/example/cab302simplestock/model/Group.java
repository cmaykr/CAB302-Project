package com.example.cab302simplestock.model;

public class Group {
    int groupID;
    String groupName;
    int ownerID;

    public Group(String groupName, int groupOwnerID)
    {
        this.groupName = groupName;
        this.ownerID = groupOwnerID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        if (groupName.isEmpty())
        {
            throw new IllegalArgumentException("Group name cannot be empty.");
        }
        this.groupName = groupName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int newOwnerID) {
        if (newOwnerID <= 0)
        {
            throw new IllegalArgumentException("New owner ID must be positive, >0.");
        }
        this.ownerID = newOwnerID;
    }

    public void setGroupID(int groupID) {
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID must be positive, >0.");
        }
        this.groupID = groupID;
    }

    public int getGroupID() {
        return groupID;
    }
}
