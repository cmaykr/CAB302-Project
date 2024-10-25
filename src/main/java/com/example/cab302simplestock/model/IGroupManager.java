package com.example.cab302simplestock.model;

import java.util.List;

/**
 * Interface for group manager, used to manage groups in a database.
 * Defines CRUD methods for Groups, and also extra methods for objects associated with groups, such as categories and users.
 */
public interface IGroupManager {

    /**
     * CRUD method for adding a group to a database.
     * @param group The group to add.
     * @return The ID in the database for the newly added group.
     */
    int addGroup(Group group);

    /**
     * CRUD method for updating a group in the database. Should use a DAO update method in the implementation.
     * @param group The group to update and its new values.
     */
    void updateGroup(Group group);

    /**
     * CRUD method for deleting a group in the database. Should use a DAO delete method.
     * @param group The group to delete, which value is used to delete the group is implementation specific.
     */
    void deleteGroup(Group group);

    /**
     * CRUD method to get all groups that exist in the database. Should use a DAO read method.
     * @return A list of all groups in the database.
     */
    List<Group> getAllGroups();

    /**
     * Searches and finds all users with access to view a specific group.
     * @param groupID The ID of the group to search in.
     * @return A list of all users with access to the group.
     */
    List<User> getUsers(int groupID);

    /**
     * Finds a single user with access to view a group, the search should be based on the email value of that user.
     * @param userEmail The email of the user to search for. Handling of an empty value is implementation specific.
     * @param groupID The ID of the group to search in. Invalid ID values need to be handled correctly.
     * @return The user if found with that email and with access to that group.
     */
    User findUser(String userEmail, int groupID);

    /**
     * Adds a new user with view access to a group
     * @param user The user to add to the group.
     * @param groupID The ID of the group to add the user to.
     * @return The ID of the user that was added to the group.
     */
    int addUser(User user, int groupID);

    /**
     * Deletes view access for a user in a group.
     * @param userID The ID of the user to delete group access for.
     * @param groupID The ID of the group the user should be deleted from.
     */
    void deleteUser(int userID, int groupID);

    /**
     * Gets all categories that exist in a group.
     * @param groupID The ID of the group to search in.
     * @return A list of all categories in the group.
     */
    List<Category> getCategories(int groupID);

    /**
     * Finds a specific category in a group based on the name of the category.
     * @param categoryName The name of the category.
     * @param groupID The ID of the group to search in.
     * @return The found category with an identical name as the parameter one and in the group.
     */
    Category findCategory(String categoryName, int groupID);
}
