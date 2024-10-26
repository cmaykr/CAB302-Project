package com.example.cab302simplestock.model.Managers;

import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;
import com.example.cab302simplestock.model.ManagerInterfaces.IUserManager;
import com.example.cab302simplestock.model.ManagerInterfaces.IViewUserManager;
import com.example.cab302simplestock.model.User;
import com.example.cab302simplestock.model.ViewUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the viewUserManager interface using a viewUser DAO for the managing of viewUsers.
 * Uses a user manager to handle methods that interact directly with users.
 * Uses dependency injection to get the viewUser DAO and the user manager.
 */
public class ViewUserManager implements IViewUserManager {
    IViewUserDAO viewUserDAO;
    IUserManager userManager;

    /**
     * Constructs a new viewUser manager with the viewUser DAO and user manager injected.
     * @param viewUserDAO The viewUser DAO to use.
     * @param userManager The user manager to use.
     */
    public ViewUserManager(IViewUserDAO viewUserDAO, IUserManager userManager) {
        this.viewUserDAO = viewUserDAO;
        this.userManager = userManager;
    }

    /**
     * Adds a new viewUser to the database, all values for the viewUser should be set, except for the ID value which is set by the database.
     * @param viewUser The viewUser to add.
     * @return The ID of the newly added viewUser.
     */
    @Override
    public int addViewUser(ViewUser viewUser) {
        return viewUserDAO.addViewUser(viewUser);
    }

    /**
     * Updates an existing viewUser in the database, every value should be set in the viewUser parameter.
     * The viewUser in the database will have every single value overwritten by this method.
     * Any empty value set in the parameter will overwrite the old value with an empty value.
     * @param viewUser The viewUser with its values to update. The ID must be set for the database to find the viewUser.
     */
    @Override
    public void updateViewUser(ViewUser viewUser) {
        viewUserDAO.updateViewUser(viewUser);
    }

    /**
     * Deletes a viewUser from the database, only the ID value for the viewUser to delete needs to be set.
     * If this method is called it should be assumed the viewUser is gone and unrecoverable.
     * @param viewUser The viewUser to delete. Only the ID needs to be set, as the database uses the ID to find the viewUser.
     */
    @Override
    public void deleteViewUser(ViewUser viewUser) {
        viewUserDAO.deleteViewUser(viewUser);
    }

    /**
     * Gets all viewUsers that exist in the database.
     * @return A list of all viewUsers.
     */
    @Override
    public List<ViewUser> getAllViewUsers() {
        return viewUserDAO.getAllViewUsers();
    }

    /**
     * Finds and gets all users that have access to a group.
     * @param groupID The group to find which users have access to.
     * @return A list of all users with access to the group.
     */
    @Override
    public List<User> getUsersInGroup(int groupID) {
        List<ViewUser> viewUsers = viewUserDAO.getViewUsersByGroupID(groupID);

        List<Integer> IDs = new ArrayList<>();
        for (ViewUser viewUser: viewUsers) {
            IDs.add(viewUser.getUserID());
        }

        return userManager.getUsersByIDs(IDs);
    }

    /**
     * Searches for and gets a user in a group based on the users ID
     * @param userID The ID of the users to search for.
     * @param groupID The ID of the group to search in.
     * @return The user if found in the group, i.e. the user has access to the group.
     */
    @Override
    public User findUserInGroup(int userID, int groupID) {
        for (ViewUser viewUser:viewUserDAO.getAllViewUsers()) {
            if (viewUser.getUserID() == userID && viewUser.getGroupID() == groupID)
            {
                List<Integer> userIDs = new ArrayList<>();
                userIDs.add(viewUser.getID());
                return userManager.getUsersByIDs(userIDs).getFirst();
            }
        }
        return null;
    }

    /**
     * Grants access for a user to a specific group.
     * Should create a viewUser with the user and group for its values.
     * @param user The user to grant access to.
     * @param groupID The ID of the group the user should have access to.
     * @return The ID of the newly created viewUser.
     */
    @Override
    public int addUserToGroup(User user, int groupID) {
        ViewUser viewUser = new ViewUser(groupID, user.getID());
        return viewUserDAO.addViewUser(viewUser);
    }

    /**
     * Finds and gets a viewUser based on the user and group it references.
     * @param userID The ID of the user it references.
     * @param groupID The ID of the group it references.
     * @return The found viewUser.
     */
    @Override
    public ViewUser findViewUser(int userID, int groupID) {
        List<ViewUser> viewUsers = viewUserDAO.getAllViewUsers();
        for (ViewUser viewUser:viewUsers) {
            if (viewUser.getID() == userID && viewUser.getGroupID() == groupID)
            {
                return viewUser;
            }
        }
        return null;
    }
}
