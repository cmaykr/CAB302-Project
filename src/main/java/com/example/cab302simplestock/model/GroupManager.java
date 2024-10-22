package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;

import java.util.List;
import java.util.Objects;

public class GroupManager implements IGroupManager {
    IGroupDAO groupDAO;
    ICategoryManager categoryManager;
    IViewUserManager viewUserManager;

    /**
     * Constructs a groupManager with injected DAOs for group, category and the viewUser.
     * @param groupDAO The injected groupDAO
     * @param categoryManager The injected categoryManager
     * @param viewUserManager The injected viewUserDAO
     */
    public GroupManager(IGroupDAO groupDAO, ICategoryManager categoryManager, IViewUserManager viewUserManager) {
        this.groupDAO = groupDAO;
        this.categoryManager = categoryManager;
        this.viewUserManager = viewUserManager;
    }

    /**
     * Creates a new group into the chosen DAO, e.g. Sqlite database.
     * Uses the groupDAO method to add the group.
     * @param group The group to create.
     * @return The newly created ID of the group. If the group already exists the existing group ID is returned.
     */
    @Override
    public int addGroup(Group group) {
        return groupDAO.addGroup(group);
    }

    /**
     * Update a group in the chosen DAO, e.g. Sqlite database.
     * Uses the groupDAO method to update the group.
     * @param group The group to update.
     */
    @Override
    public void updateGroup(Group group) {
        groupDAO.updateGroup(group);
    }

    /**
     * Delete a group in the chosen DAO, e.g. Sqlite database.
     * Uses the groupDAO method to delete the group.
     * @param group The group to delete.
     */
    @Override
    public void deleteGroup(Group group) {
        groupDAO.deleteGroup(group);
    }

    /**
     * Get all groups in the database.
     * Uses the groupDAO method getAllGroups.
     * @return A list of all groups.
     */
    @Override
    public List<Group> getAllGroups() {
        return groupDAO.getAllGroups();
    }

    /**
     * Gets all the users that can view a group.
     * Uses the viewUserManager to get all users in the group.
     * @param groupID The group to get all users in.
     * @return A list of all users with access to the group.
     */
    @Override
    public List<User> getUsers(int groupID) {
        return viewUserManager.getUsersInGroup(groupID);
    }

    /**
     * Finds a specific user based on the users email, which is unique.
     * @param userEmail The email to search for a user.
     * @param groupID The group which the search is made in.
     * @return The user if found, otherwise null.
     */
    @Override
    public User findUser(String userEmail, int groupID) {
        List<User> users = getUsers(groupID);

        for (User user: users)
        {
            if (Objects.equals(user.getEmail(), userEmail))
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public int addUser(User user, int groupID) {
        return viewUserManager.addUserToGroup(user, groupID);
    }

    @Override
    public void deleteUser(int userID, int groupID) {
        ViewUser viewUserToDelete = viewUserManager.findViewUser(userID, groupID);

        viewUserManager.deleteViewUser(viewUserToDelete);
    }

    @Override
    public List<Category> getCategories(int groupID) {
        return categoryManager.getCategoriesInGroup(groupID);
    }

    @Override
    public Category findCategory(String categoryName, int groupID) {
        List<Category> categories = categoryManager.getCategoriesInGroup(groupID);

        for (Category cat: categories)
        {
            if (Objects.equals(cat.getCategoryName(), categoryName))
            {
                return cat;
            }
        }
        return null;
    }

    @Override
    public int addCategory(Category category) {
       return categoryManager.addCategoryToGroup(category);
    }

    @Override
    public void deleteCategory(int categoryID) {
        categoryManager.deleteCategory(categoryID);

    }
}
