package com.example.cab302simplestock.model;

import java.util.List;

public interface IUserManager {

    void addUser(User user);

    List<User> getUsersByIDs(List<Integer> iDs);
}
