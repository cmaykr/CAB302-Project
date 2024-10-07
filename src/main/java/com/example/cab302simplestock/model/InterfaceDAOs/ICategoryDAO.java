package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Category;
import java.util.List;

public interface ICategoryDAO {
    public void addCategory(Category category);

    public void updateCategory(Category category);

    public void deleteCategory(Category category);

    public List<Category> getAllCategories();
}
