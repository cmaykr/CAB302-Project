package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;
import java.util.List;

/**
 * Implementation of the userManager interface using a userDAO for the managing of users.
 * Uses dependency injection to get the user DAO.
 */
public class UserManager implements IUserManager{
    IUserDAO userDAO;

    /**
     * Constructs an instance of the manager, the user DAO is injected with this constructor.
     * @param userDAO The user DAO to use.
     */
    public UserManager(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Adds a new user to the database, all values for the User should be set, except for the ID value which is set by the database.
     * @param user The user to add.
     * @return The ID of the newly added user.
     */
    @Override
    public int addUser(User user) {
       return userDAO.addUser(user);
    }

    /**
     * Updates an existing user in the database, every value should be set in the User parameter.
     * The user in the database will have every single value overwritten by this method.
     * Any empty value set in the parameter will overwrite the old value with an empty value.
     * @param user The user with its values to update. The ID must be set for the database to find the user.
     */
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    /**
     * Deletes a user from the database, only the ID value for the user to delete needs to be set.
     * If this method is called it should be assumed the user is gone and unrecoverable.
     * @param user The user to delete. Only the ID needs to be set, as the database uses the ID to find the user.
     */
    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    /**
     * Gets all users that exist in the database.
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Gets all users in the database that has an ID that corresponds to one of the IDs in the inputted list.
     * @param iDs A list of all IDs the caller wants the users for.
     * @return A list of all found users.
     */
    @Override
    public List<User> getUsersByIDs(List<Integer> iDs) {
        return userDAO.getUsersByIDs(iDs);
    }
}
