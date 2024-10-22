package com.example.cab302simplestock.model;

import java.util.List;

public interface IGroupManager {

    int addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroup(Group group);

    List<Group> getAllGroups();

    List<User> getUsers(int groupID);

    User findUser(String userEmail, int groupID);

    int addUser(User user, int groupID);

    void deleteUser(int userID, int groupID);

    List<Category> getCategories(int groupID);

    int addCategory(Category category);

    Category findCategory(String categoryName, int groupID);

    void deleteCategory(int categoryID);
}
