package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.ViewUser;

import java.util.List;

/**
 * CRUD interface for interacting with viewUser's in a database.
 */
public interface IViewUserDAO {
    /**
     * Adds a new viewUser to a database. The ID should be set by the database.
     *
     * @param viewUser The viewUser to add.
     * @return
     */
    int addViewUser(ViewUser viewUser);

    /**
     * Updates an viewUser in the database, the viewUser ID should be used to find which viewUser to update.
     * @param viewUser The viewUser to update, the ID should be set.
     */
    void updateViewUser(ViewUser viewUser);

    /**
     * Deletes a viewUser in a database, should be chosen by the viewUser ID.
     * @param viewUser The viewUser to delete, the ID value should be set.
     */
    void deleteViewUser(ViewUser viewUser);

    /**
     * Gets all viewUsers in a database, should set all values in the ViewUser model.
     * @return A list of viewUsers.
     */
    List<ViewUser> getAllViewUsers();

    List<ViewUser> getViewUsersByGroupID(int groupID);
}
