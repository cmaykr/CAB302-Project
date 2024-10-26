package com.example.cab302simplestock.model.Managers;

import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;
import com.example.cab302simplestock.model.ManagerInterfaces.ICategoryManager;
import com.example.cab302simplestock.model.ManagerInterfaces.IGroupManager;
import com.example.cab302simplestock.model.ManagerInterfaces.IViewUserManager;
import com.example.cab302simplestock.model.User;
import com.example.cab302simplestock.model.ViewUser;

import java.util.List;
import java.util.Objects;

/**
 * Manager class for interacting with the group and directly connected entities to group, categories and viewUser.
 * Has the basic CRUD methods for groups, also has extra methods that references a group or multiple groups.
 * The manager uses other managers for objects and database interactions that don't directly correlate to groups.
 * All dependencies are injected in the constructor.
 */
public class GroupManager implements IGroupManager {
    IGroupDAO groupDAO;
    ICategoryManager categoryManager;
    IViewUserManager viewUserManager;

    /**
     * Constructs a groupManager with injected DAOs for group, category and viewUser take their own respective managers.
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
     * Gets all the users that can view a group and puts them in a list.
     * Uses the viewUserManager to get all users in the group.
     * If no users are found the return value will be null.
     * @param groupID The group to get all users in.
     * @return A list of all users with access to the group. Can be null
     */
    @Override
    public List<User> getUsers(int groupID) {
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID set to invalid value, must be a positive value, >0.");
        }
        return viewUserManager.getUsersInGroup(groupID);
    }

    /**
     * Finds a specific user in a group based on the users email.
     * No other checks are made for the user except for its email.
     * @param userEmail The email to search for a user.
     * @param groupID The group which the search is made in.
     * @return The user if found, otherwise null.
     */
    @Override
    public User findUser(String userEmail, int groupID) {
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID in findUser invalid value, must be positive, >0.");
        }
        List<User> users = getUsers(groupID);
        // Only one category exists in the group.

        for (User user: users)
        {
            if (Objects.equals(user.getEmail(), userEmail) && viewUserManager.findUserInGroup(user.getID(), groupID) != null)
            {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a user to be able to view a specific group
     * Uses the viewUserManager to add a user to allow for database specific implementations.
     * @param user The user to add to the group.
     * @param groupID The ID of the group to add the user to.
     * @return The ID of the newly added user.
     */
    @Override
    public int addUser(User user, int groupID) {
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID parameter in addUser invalid value, must be positive, >0.");
        }
        return viewUserManager.addUserToGroup(user, groupID);
    }

    /**
     * Deletes a user from being able to view a group, it does not delete the actual user.
     * If this method is called it should be assumed the user can no longer view the group.
     * @param userID The ID of the user to delete from the group.
     * @param groupID The ID of the group the user should be deleted from.
     */
    @Override
    public void deleteUser(int userID, int groupID) {
        if (userID <= 0)
        {
            throw new IllegalArgumentException("User ID parameter in deleteUser has invalid value, must be a positive value, >0.");
        }
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID parameter in deleteUser has invalid value, must be a positive value, >0.");
        }
        ViewUser viewUserToDelete = viewUserManager.findViewUser(userID, groupID);

        viewUserManager.deleteViewUser(viewUserToDelete);
    }

    /**
     * Gets all the categories that are in a group.
     * Uses the categoryManager to allow for multiple different implementations.
     * @param groupID The ID of the group to search in.
     * @return A list of the categories in the group.
     */
    @Override
    public List<Category> getCategories(int groupID) {
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID in getCategories has invalid value, must be a positive value, >0.");
        }
        return categoryManager.getCategoriesInGroup(groupID);
    }

    /**
     * Searches and finds a specific category by its name in a group.
     * No other checks are made for the category except that its name is identical to the chosen one and its in the group to search in.
     * @param categoryName The name of the category to search for, must be an exact name, no wildcards can be used.
     * @param groupID The ID of the group to search in.
     * @return The category if found, otherwise null.
     */
    @Override
    public Category findCategory(String categoryName, int groupID) {
        if (groupID <= 0)
        {
            throw new IllegalArgumentException("Group ID parameter in findCategory has an invalid value, must be a positive value, >0.");
        }
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

    /**
     * Adds a new category to the database.
     * @param category The category to add
     * @return The ID of the newly added category.
     */
    @Override
    public int addCategory(Category category) {
        return categoryManager.addCategory(category);
    }

    /**
     * Deletes a category in the database.
     * @param categoryID The ID of the category to delete.
     */
    @Override
    public void deleteCategory(int categoryID) {
        if (categoryID <= 0)
        {
            throw new IllegalArgumentException("Category ID in deleteCategory has an invalid value, must be a positive value, >0.");
        }
        categoryManager.deleteCategory(categoryID);
    }
}
