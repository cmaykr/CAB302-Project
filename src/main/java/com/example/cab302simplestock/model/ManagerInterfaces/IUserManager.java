package com.example.cab302simplestock.model.ManagerInterfaces;

import com.example.cab302simplestock.model.User;

import java.util.List;

/**
 * Interface for user manager, defining CRUD methods and extra methods for searching users in the database.
 */
public interface IUserManager {

    /**
     * CRUD method to add a new user to the database.
     * @param user The user to add.
     * @return The ID of the newly added user.
     */
    int addUser(User user);

    /**
     * CRUD method to update a user that exists in the database.
     * How the update happens is implementation specific.
     * @param user The user with its values to update.
     */
    void updateUser(User user);

    /**
     * CRUD method to delete a user from the database. Which values are used to find the user to delete is implementation specific.
     * @param user The user to delete. Only needs the values that are needed to find the user.
     */
    void deleteUser(User user);

    /**
     * CRUD method to get all users that exist in the database.
     * @return A list of all users.
     */
    List<User> getAllUsers();

    /**
     * Takes a list of IDs and finds every user in the database that has a corresponding ID in the inputted list.
     * @param iDs A list of all IDs the caller wants the user for.
     * @return A list of the found users.
     */
    List<User> getUsersByIDs(List<Integer> iDs);
}
