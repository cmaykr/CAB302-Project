package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;
import com.example.cab302simplestock.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation for the User DAO interface.
 */
public class MockUserDAO implements IUserDAO {
    List<User> users;

    /**
     * Constructs the mock DAO.
     */
    public MockUserDAO() {
        users = new ArrayList<>();
    }

    /**
     * Adds a new user to the mock database.
     * @param user The user that should be added to the database.
     * @return The ID of the added user.
     */
    @Override
    public int addUser(User user) {
        if (users.isEmpty())
        {
            user.setID(1);
        }
        else
            user.setID(users.getLast().getID()+1);

        users.add(user);

        return user.getID();
    }

    /**
     * Updates a user in the mock database.
     * @param user The user that should be updated.
     */
    @Override
    public void updateUser(User user) {
        for (int i=0;i<users.size(); i++) {
            if (users.get(i).getID() == user.getID()) {
                users.set(i, user);
            }
        }
    }

    /**
     * Deletes a user in the mock database.
     * @param user The user to be deleted. The ID value should be set.
     */
    @Override
    public void deleteUser(User user) {
        users.removeIf(viewUse -> viewUse.getID() == user.getID());
    }

    /**
     * Gets all users that exist in the mock database.
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Searches and gets all users that have an ID which is in the inputted list of IDs.
     * @param iDs A list of all IDs to search for.
     * @return A list of all users that have their ID correspond to one in the ID list.
     */
    @Override
    public List<User> getUsersByIDs(List<Integer> iDs) {
        List<User> foundUsers = new ArrayList<>();

        for (User user : users) {
            for (Integer iD : iDs) {
                if (user.getID() == iD) {
                    foundUsers.add(user);
                }
            }
        }
        return foundUsers;
    }
}
