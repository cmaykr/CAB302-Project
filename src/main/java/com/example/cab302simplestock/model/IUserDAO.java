package com.example.cab302simplestock.model;

import java.util.List;

public interface IUserDAO {

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

    public User getUser(String firstName, String lastName);

    public List<User> getAllUsers();
}
