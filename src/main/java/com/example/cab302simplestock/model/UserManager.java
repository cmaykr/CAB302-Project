package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;

import java.util.List;

public class UserManager implements IUserManager{
    IUserDAO userDAO;

    public UserManager(IUserDAO userDAO) {

    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public List<User> getUsersByIDs(List<Integer> iDs) {
        return List.of();
    }
}
