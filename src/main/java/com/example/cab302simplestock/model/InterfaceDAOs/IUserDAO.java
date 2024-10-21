package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.User;

import java.util.List;

/**
 * CRUD interface for interacting with users in a database.
 */
public interface IUserDAO {

    /**
     * Adds a new user to a database. The ID should be set by the database.
     * @param user The user to add.
     */
    public void addUser(User user);

    /**
     * Updates an user in the database, the user ID should be used to find which user to update.
     * @param user The user to update, the ID should be set.
     */
    public void updateUser(User user);

    /**
     * Deletes a user in a database, should be chosen by the user ID.
     * @param user The item to delete, the ID value should be set.
     */
    public void deleteUser(User user);

    /**
     * Gets all users in a database, should set all values in the User model.
     * @return A list of users.
     */
    public List<User> getAllUsers();
}
