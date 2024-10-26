package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import java.util.List;

/**
 * Manager class for Category interacting with categories in a database using the category DAO.
 * The DAO is injected by the constructor.
 * The Manager implements the DAO CRUD methods but also extra more specific search methods.
 */
public class CategoryManager implements ICategoryManager {
    ICategoryDAO categoryDAO;

    /**
     * Constructs an instance of categoryManager with an injected category DAO.
     * The injected category DAO is the DAO and database the categoryManager will use for its methods.
     * @param categoryDAO The DAO to inject.
     */
    public CategoryManager(ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * Gets all categories in the database that are in a specific group and adds them to a list.
     * Uses the DAO method to get the categories.
     * @param groupID The ID of the group to get all categories in.
     * @return A list of categories in a group.
     */
    @Override
    public List<Category> getCategoriesInGroup(int groupID) {
        return categoryDAO.getCategoriesByGroupID(groupID);
    }

    /**
     * Finds a specific category in a specific group. The search is made by the ID of the category and the ID of the group it should be searched in.
     * @param categoryID The ID of the category to search for.
     * @param groupID The ID of the group to limit the search in.
     * @return The found category, or null if no category exists with that ID or in that specific group.
     */
    @Override
    public Category findCategoryInGroup(int categoryID, int groupID) {
        List<Category> categoriesInGroup = categoryDAO.getCategoriesByGroupID(groupID);

        for (Category cat: categoriesInGroup) {
            if (cat.getCategoryID() == categoryID)
            {
                return cat;
            }
        }

        return null;
    }

    /**
     * Adds a category to a group, uses the DAO method to add it to the database.
     * @param category The category to add to the database.
     * @return The ID of the newly added category.
     */
    @Override
    public int addCategory(Category category) {
        return categoryDAO.addCategory(category);
    }

    /**
     * Deletes a category with the inputted ID value from a database.
     * No checks are made if the category can be deleted, if it exists or any other checks. ONLY the ID is compared.
     * If this method is called it should be assumed the category with that ID WILL have been deleted.
     * @param categoryID The ID of the category to delete.
     */
    @Override
    public void deleteCategory(int categoryID) {
        Category cat = new Category("", 1);
        cat.setCategoryID(categoryID);
        categoryDAO.deleteCategory(cat);
    }

    /**
     * Searches the database for a category with a name identical to the inputted one, it searches for the category in a specific group.
     * The search uses the category DAO method with the same name to allow the DAO implementation to handle the search.
     * @param categoryName The name of the category to search for.
     * @param groupID The ID of the group to search in.
     * @return The found category with the name identical to the parameter one, or null if no category was found.
     */
    @Override
    public Category findCategoryInGroupByName(String categoryName, int groupID) {
        return categoryDAO.getCategoryInGroupByName(categoryName, groupID);
    }

    /**
     * Gets every category that exists in the database, no extra checks are made.
     * Uses the DAO method by the same name to allow for different implementations.
     * @return A list of all categories returned by the DAO method of the same name. All categories in the database.
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
