package com.example.cab302simplestock.model;

import java.util.List;

public interface IViewUserManager {

    int addViewUser(ViewUser viewUser);

    void updateViewUser(ViewUser viewUser);

    void deleteViewUser(ViewUser viewUser);

    List<ViewUser> getAllViewUsers();

    List<User> getUsersInGroup(int groupID);

    User findUserInGroup(int userID, int groupID);

    int addUserToGroup(User user, int groupID);

    void deleteUserFromGroup(int userID, int groupID);

    ViewUser findViewUser(int userID, int groupID);
}
