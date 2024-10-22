package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import java.util.List;

public class CategoryManager implements ICategoryManager {
    ICategoryDAO categoryDAO;

    public CategoryManager(ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getCategoriesInGroup(int groupID) {
        return categoryDAO.getCategoriesByGroupID(groupID);
    }

    @Override
    public Category findCategoryInGroup(int categoryID, int groupID) {
        return null;
    }

    @Override
    public int addCategoryToGroup(Category category) {
        return categoryDAO.addCategory(category);
    }

    @Override
    public void deleteCategory(int categoryID) {
        Category cat = new Category("", 1);
        cat.setCategoryID(categoryID);
        categoryDAO.deleteCategory(cat);
    }

    @Override
    public Category findCategoryInGroupByName(String categoryName, int groupID) {
        return categoryDAO.getCategoryInGroupByName(categoryName, groupID);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
