package com.example.cab302simplestock.model;

import java.util.List;

public interface ICategoryManager {

    List<Category> getCategoriesInGroup(int groupID);

    Category findCategoryInGroup(int categoryID, int groupID);

    int addCategoryToGroup(Category category);

    void deleteCategory(int categoryID);
}
