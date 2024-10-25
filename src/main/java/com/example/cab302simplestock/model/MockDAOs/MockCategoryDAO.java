package com.example.cab302simplestock.model.MockDAOs;

import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MockCategoryDAO implements ICategoryDAO {
    List<Category> categories;
    public MockCategoryDAO() {
        categories = new ArrayList<>();
    }
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

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

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
