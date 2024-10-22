package com.example.cab302simplestock.model;

import java.util.List;

public interface IViewUserManager {

    List<User> getUsersInGroup(int groupID);

    User findUserInGroup(int userID, int groupID);

    int addUserToGroup(User user, int groupID);

    void deleteUserFromGroup(int userID, int groupID);

}
