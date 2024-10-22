package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;
import com.example.cab302simplestock.model.MockDAOs.MockCategoryDAO;
import com.example.cab302simplestock.model.MockDAOs.MockGroupDAO;
import com.example.cab302simplestock.model.MockDAOs.MockViewUserDAO;

import java.util.List;

public class GroupManager implements IGroupManager {
    public GroupManager(IGroupDAO mockGroupDAO, ICategoryDAO mockCategoryDAO, IViewUserDAO mockViewUserDAO) {
    }

    @Override
    public int addGroup(Group group) {
        return 0;
    }

    @Override
    public void updateGroup(Group group) {

    }

    @Override
    public void deleteGroup(Group group) {

    }

    @Override
    public List<Group> getAllGroups() {
        return List.of();
    }

    @Override
    public List<User> getUsers(int groupID) {
        return List.of();
    }

    @Override
    public User findUser(String userEmail, int groupID) {
        return null;
    }

    @Override
    public int addUser(User user, int groupID) {
        return 0;
    }

    @Override
    public void deleteUser(int userID, int groupID) {

    }

    @Override
    public List<Category> getCategories(int groupID) {
        return List.of();
    }

    @Override
    public Category findCategory(String categoryName, int groupID) {
        return null;
    }

    @Override
    public int addCategory(Category category) {
        return 0;
    }

    @Override
    public void deleteCategory(int categoryID) {

    }
}
