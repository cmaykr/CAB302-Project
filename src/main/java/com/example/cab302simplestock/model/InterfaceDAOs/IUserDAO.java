package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.User;

import java.util.List;

public interface IUserDAO {

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public List<User> getAllUsers();
}
