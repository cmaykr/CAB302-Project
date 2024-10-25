package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mock implementation for the category DAO interface.
 */
public class MockCategoryDAO implements ICategoryDAO {
    List<Category> categories;

    /**
     * Constructs the mock DAO.
     */
    public MockCategoryDAO() {
        categories = new ArrayList<>();
    }

    /**
     * Adds a new category to the mock database.
     * @param category The category that should be added to the database.
     * @return The ID of the added category.
     */
    @Override
    public int addCategory(Category category) {
        if (categories.isEmpty())
        {
            category.setCategoryID(1);
        }
        else
            category.setCategoryID(categories.getLast().getCategoryID()+1);

        categories.add(category);
        return category.getCategoryID();
    }

    /**
     * Updates a category in the mock database.
     * @param category The category that should be updated.
     */
    @Override
    public void updateCategory(Category category) {
        int categoryID = -1;
        for (Category cat: categories)
        {
            if (cat.getCategoryID() == category.getCategoryID())
            {
                categoryID = cat.getCategoryID();
                break;
            }
        }

        if (categoryID != -1)
        {
            categories.set(categoryID, category);
        }
    }

    /**
     * Deletes a category in the mock database.
     * @param category The category to be deleted. The ID value should be set.
     */
    @Override
    public void deleteCategory(Category category) {
        int categoryID = -1;
        for (Category cat: categories)
        {
            if (cat.getCategoryID() == category.getCategoryID())
            {
                categoryID = cat.getCategoryID();
                break;
            }
        }

        if (categoryID != -1)
        {
            for (Category category1:categories) {
                if (category1.getCategoryID() == categoryID)
                {
                    categories.remove(category1);
                    break;
                }
            }
        }
    }

    /**
     * Gets all categories that exist in the mock database.
     * @return A list of all categories.
     */
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    /**
     * Searches and gets a category where the search is based on the categories name and searches in a specific group.
     * @param categoryName The name of the category to search for.
     * @param groupID The ID of the group to search in.
     * @return The category if found, otherwise null.
     */
    @Override
    public Category getCategoryInGroupByName(String categoryName, int groupID) {
        for (Category category: categories)
        {
            if (Objects.equals(category.getCategoryName(), categoryName))
            {
                return category;
            }
        }
        return null;
    }

    /**
     * Gets all categories that exist in a group in the mock database.
     * @param groupID The ID of the group to get all categories for.
     * @return A list of all categories in the group.
     */
    @Override
    public List<Category> getCategoriesByGroupID(int groupID) {
        List<Category> categoriesInGroup = new ArrayList<>();
        for (Category category: categories)
        {
            if (category.getGroupID() == groupID)
            {
                categoriesInGroup.add(category);
            }
        }
        return categoriesInGroup;
    }
}
