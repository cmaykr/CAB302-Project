package com.example.cab302simplestock.model.ManagerInterfaces;

import com.example.cab302simplestock.model.Category;

import java.util.List;

/**
 * Interface for managing categories in the database.
 * Defines the simple CRUD methods for category and some extra, only a few basic methods are defined to allow for implementations based on the need of future changes.
 */
public interface ICategoryManager {

    /**
     * Finds all categories that exist in a group.
     * @param groupID The ID of the group to search in.
     * @return A list of the categories.
     */
    List<Category> getCategoriesInGroup(int groupID);

    /**
     * Searches and finds a single category in a group. Search is based on the ID of that category.
     * @param categoryID The ID of the category to find.
     * @param groupID The ID of the group to search in.
     * @return The category with its values if found.
     */
    Category findCategoryInGroup(int categoryID, int groupID);

    /**
     * Adds a new category to the database, should use a DAO add method.
     * @param category The category to add.
     * @return The ID value in the database for the newly added category.
     */
    int addCategory(Category category);

    /**
     * CRUD method for deleting a category based on its ID value.
     * If method is called it should be assumed the category is deleted and unrecoverable.
     * @param categoryID The ID of the category to delete.
     */
    void deleteCategory(int categoryID);

    /**
     * Searches for a category in a group based on the categories name.
     * @param categoryName The name of the category to search for.
     * @param groupID The ID of the group to search in.
     * @return The category in a group with the same name as the categoryName parameter.
     */
    Category findCategoryInGroupByName(String categoryName, int groupID);

    /**
     * Gets all categories that exist in the database.
     * @return A list of all categories.
     */
    List<Category> getAllCategories();
}
