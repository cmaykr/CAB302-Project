package com.example.cab302simplestock.model;

/**
 * Simple model class for representing a Group with a name and the ID of the owner.
 */
public class Group {
    int groupID;
    String groupName;
    int ownerID;

    /**
     * Constructor for constructing a new group with a group name and owner ID.
     * @param groupName The name of the group.
     * @param groupOwnerID The ID of the owner.
     */
    public Group(String groupName, int groupOwnerID)
    {
        this.groupName = groupName;
        this.ownerID = groupOwnerID;
    }

    /**
     * Gets the name of the group.
     * @return The group name of type String.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets a new group name, checks if the new name is invalid.
     * @param groupName The new group name
     * @throws IllegalArgumentException If the name parameter is empty.
     */
    public void setGroupName(String groupName) throws IllegalArgumentException {
        if (groupName.isEmpty())
        {
            throw new IllegalArgumentException("Group name cannot be empty.");
        }
        this.groupName = groupName;
    }

    /**
     * Gets the ID of the owner of the group.
     * @return The owner ID of type integer.
     */
    public int getOwnerID() {
        return ownerID;
    }

    /**
     * Sets a new owner ID for the group. The new ID must be positive, >0.
     * @param newOwnerID The new ID for the owner.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setOwnerID(int newOwnerID) throws IllegalArgumentException {
        if (newOwnerID <= 0)
        {
            throw new IllegalArgumentException("New owner ID must be positive, >0.");
        }
        this.ownerID = newOwnerID;
    }

    /**
     * Sets a new group ID. The new ID must be positive, >0.
     * @param groupID The new ID.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setGroupID(int groupID) throws IllegalArgumentException {
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID must be positive, >0.");
        }
        this.groupID = groupID;
    }

    /**
     * Gets the group ID.
     * @return The group ID of type integer.
     */
    public int getGroupID() {
        return groupID;
    }
}
