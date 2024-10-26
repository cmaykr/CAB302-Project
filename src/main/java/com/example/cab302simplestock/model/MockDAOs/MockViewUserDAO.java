package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;
import com.example.cab302simplestock.model.ViewUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation for the ViewUser DAO interface.
 */
public class MockViewUserDAO implements IViewUserDAO {
    List<ViewUser> viewUsers;

    /**
     * Constructs the mock DAO.
     */
    public MockViewUserDAO() {
        viewUsers = new ArrayList<>();
    }

    /**
     * Adds a new viewUser to the mock database.
     * @param viewUser The viewUser that should be added to the database.
     * @return The ID of the added viewUser.
     */
    @Override
    public int addViewUser(ViewUser viewUser) {
        if (viewUsers.isEmpty())
        {
            viewUser.setID(1);
        }
        else
            viewUser.setID(viewUsers.getLast().getGroupID()+1);

        viewUsers.add(viewUser);

        return viewUser.getID();
    }

    /**
     * Updates a viewUser in the mock database.
     * @param viewUser The viewUser that should be updated.
     */
    @Override
    public void updateViewUser(ViewUser viewUser) {
        for (int i=0;i<viewUsers.size(); i++) {
            if (viewUsers.get(i).getID() == viewUser.getID()) {
                viewUsers.set(i, viewUser);
            }
        }
    }

    /**
     * Deletes a viewUser in the mock database.
     * @param viewUser The viewUser to be deleted. The ID value should be set.
     */
    @Override
    public void deleteViewUser(ViewUser viewUser) {
        viewUsers.removeIf(viewUse -> viewUse.getID() == viewUser.getID());
    }

    /**
     * Gets all ViewUsers that exist in the mock database.
     * @return A list of all ViewUsers.
     */
    @Override
    public List<ViewUser> getAllViewUsers() {
        return viewUsers;
    }

    /**
     * Searches and gets all viewUsers that have their groupID identical to the inputted one.
     * @param groupID The ID of the group to get all viewUsers for.
     * @return A list of all viewUsers that exist for that group.
     */
    @Override
    public List<ViewUser> getViewUsersByGroupID(int groupID) {
        List<ViewUser> viewUsersInGroup = new ArrayList<>();
        for (ViewUser viewUser: viewUsers) {
            if (viewUser.getGroupID() == groupID)
            {
                viewUsersInGroup.add(viewUser);
            }
        }
        return viewUsersInGroup;
    }
}
