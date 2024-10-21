package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Category;
import java.util.List;

/**
 * Simple CRUD interface for interacting with Category in a database.
 */
public interface ICategoryDAO {
    /**
     * Adds a new category to the DAO. The ID should be set by the database.
     * @param category The category that should be added to the database.
     */
    public void addCategory(Category category);

    /**
     * Call this for updating an already existing category in the database. Category should be chosen by the ID.
     * @param category The category that should be updated.
     */
    public void updateCategory(Category category);

    /**
     * Deletes a category in the database. The category to be deleted should be chosen by its ID.
     * @param category The category to be deleted. The ID value should be set.
     */
    public void deleteCategory(Category category);

    /**
     * Method to get all categories in a database.
     * @return A list of all categories.
     */
    public List<Category> getAllCategories();
}
