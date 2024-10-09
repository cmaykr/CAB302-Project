package com.example.cab302simplestock.model;

public class Category {
    int categoryID;
    String categoryName;
    String groupName;

    public Category(String listName, String groupName) {
        this.categoryName = listName;
        this.groupName = groupName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        if (categoryID < 0)
        {
            throw new IllegalArgumentException("Category ID cannot be negative.");
        }
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if (categoryName.isEmpty())
        {
            throw new IllegalArgumentException("Category name cannot be empty.");
        }
        this.categoryName = categoryName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        if (groupName.isEmpty())
        {
            throw new IllegalArgumentException("Category group name cannot be empty.");
        }
        this.groupName = groupName;
    }
}
