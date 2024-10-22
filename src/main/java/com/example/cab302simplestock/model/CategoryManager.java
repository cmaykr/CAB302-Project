package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import java.util.List;

public class CategoryManager implements ICategoryManager {

    public CategoryManager(ICategoryDAO categoryDAO) {
    }

    @Override
    public List<Category> getCategoriesInGroup(int groupID) {
        return List.of();
    }

    @Override
    public Category findCategoryInGroup(int categoryID, int groupID) {
        return null;
    }

    @Override
    public int addCategoryToGroup(Category category) {
        return 0;
    }

    @Override
    public void deleteCategory(int categoryID) {

    }
}
