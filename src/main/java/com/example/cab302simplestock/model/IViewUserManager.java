package com.example.cab302simplestock.model;

import java.util.List;

/**
 * Interface for a ViewUser manager defining the CRUD methods for a viewUser DAO and extra methods for searching for viewUser and other related database entities.
 * The manager is used to manage the relation between users and groups, a viewUser is a user with access to a specific group.
 * The access a user has with a group depends on the implementation of the application and how it uses the viewUser entity.
 */
public interface IViewUserManager {

    /**
     * CRUD method to add a new ViewUser to the database.
     * @param viewUser The viewUser to add to the database.
     * @return The ID of the newly added viewUser.
     */
    int addViewUser(ViewUser viewUser);

    /**
     * CRUD method to update an existing viewUser in the database.
     * Which viewUser to update depends on is implementation specific, any value can be used.
     * @param viewUser The viewUser to update,
     */
    void updateViewUser(ViewUser viewUser);

    /**
     * CRUD method to delete a viewUser in the database.
     * Finding which viewUser to delete is implementation specific, any unique values of a viewUser can be used.
     * If this is called it should be assumed the viewUser is gone and is unrecoverable.
     * @param viewUser The viewUser to delete, only values used to find it needs to have anything in them.
     */
    void deleteViewUser(ViewUser viewUser);

    /**
     * CRUD method to read and get all viewUsers that exist in the database.
     * @return A list of all viewUsers that exist.
     */
    List<ViewUser> getAllViewUsers();

    /**
     * Finds and gets all users that have access to a group.
     * @param groupID The group to find which users have access to.
     * @return A list of all users with access to the group.
     */
    List<User> getUsersInGroup(int groupID);

    /**
     * Searches for and gets a user in a group based on the users ID
     * @param userID The ID of the users to search for.
     * @param groupID The ID of the group to search in.
     * @return The user if found in the group, i.e. the user has access to the group.
     */
    User findUserInGroup(int userID, int groupID);

    /**
     * Grants access for a user to a specific group.
     * Should create a viewUser with the user and group for its values.
     * @param user The user to grant access to.
     * @param groupID The ID of the group the user should have access to.
     * @return The ID of the newly created viewUser.
     */
    int addUserToGroup(User user, int groupID);

    /**
     * Deletes a viewUser from the database, akin to removing access to a group for a user.
     * @param userID The ID of the user the viewUser references.
     * @param groupID The ID of the group the viewUser references.
     */
    void deleteUserFromGroup(int userID, int groupID);

    /**
     * Finds and gets a viewUser based on the user and group it references.
     * @param userID The ID of the user it references.
     * @param groupID The ID of the group it references.
     * @return The found viewUser.
     */
    ViewUser findViewUser(int userID, int groupID);
}
