package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;

import java.util.List;

public class MockCategoryDAO implements ICategoryDAO {
    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public void deleteCategory(Category category) {

    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }
}
