package com.example.cab302simplestock.model;

public class Category {
    String categoryName;
    String groupName;

    public Category(String categoryName, String groupName) {
        this.categoryName = categoryName;
        this.groupName = groupName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
