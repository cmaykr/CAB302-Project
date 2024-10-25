package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation for the group DAO interface.
 */
public class MockGroupDAO implements IGroupDAO {
    List<Group> groups;

    /**
     * Constructs the mock DAO.
     */
    public MockGroupDAO() {
        groups = new ArrayList<>();
    }

    /**
     * Adds a new group to the mock database.
     * @param group The category that should be added to the database.
     * @return The ID of the added group.
     */
    @Override
    public int addGroup(Group group) {
        if (groups.isEmpty())
        {
            group.setGroupID(1);
        }
        else
            group.setGroupID(groups.getLast().getGroupID()+1);

        groups.add(group);

        return group.getGroupID();
    }

    /**
     * Updates a group in the mock database.
     * @param group The group that should be updated.
     */
    @Override
    public void updateGroup(Group group) {
        int groupID = -1;
        for (Group gr: groups)
        {
            if (gr.getGroupID() == group.getGroupID())
            {
                groupID = gr.getGroupID();
                break;
            }
        }

        if (groupID != -1)
        {
            groups.set(groupID, group);
        }
    }

    /**
     * Deletes a group in the mock database.
     * @param group The group to be deleted. The ID value should be set.
     */
    @Override
    public void deleteGroup(Group group) {
        int groupID = -1;
        for (Group gr: groups)
        {
            if (gr.getGroupID() == group.getGroupID())
            {
                groupID = gr.getGroupID();
                break;
            }
        }

        if (groupID != -1)
        {
            groups.remove(groupID);
        }
    }

    /**
     * Gets all groups that exist in the mock database.
     * @return A list of all groups.
     */
    @Override
    public List<Group> getAllGroups() {
        return groups;
    }
}
