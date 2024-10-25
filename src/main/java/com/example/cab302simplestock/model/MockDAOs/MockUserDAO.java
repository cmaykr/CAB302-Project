package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;
import com.example.cab302simplestock.model.User;

import java.util.ArrayList;
import java.util.List;

public class MockUserDAO implements IUserDAO {
    List<User> users;

    public MockUserDAO() {
        users = new ArrayList<>();
    }

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

    @Override
    public void updateUser(User user) {
        for (int i=0;i<users.size(); i++) {
            if (users.get(i).getID() == user.getID()) {
                users.set(i, user);
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        users.removeIf(viewUse -> viewUse.getID() == user.getID());
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

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
