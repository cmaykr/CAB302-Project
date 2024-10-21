package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Group;

import java.util.List;

/**
 * CRUD interface for interacting with groups in a database.
 */
public interface IGroupDAO {

    /**
     * Adds a new group to the database. ID should be set by the database.
     * @param group The category that should be added to the database.
     */
    public int addGroup(Group group);

    /**
     * Method used for updating a group in a database. The group to update should be chosen by the group ID.
     * @param group The group to update, should have its ID set.
     */
    public void updateGroup(Group group);

    /**
     * Deletes a group in the database, should be chosen by the group ID.
     * @param group The group to delete based on its ID:
     */
    public void deleteGroup(Group group);

    /**
     * Gets all groups in a database, should set all values in the Group model.
     * @return A list of all groups.
     */
    public List<Group> getAllGroups();
}
