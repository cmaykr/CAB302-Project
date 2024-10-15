package com.example.cab302simplestock.model;

public class Category {
    int categoryID;
    String categoryName;
    String groupName; // DEPRECATED
    int groupID;

    /**
     * DEPRECATED, use other Category constructor instead.
     * @param listName
     * @param groupName
     */
    public Category(String listName, String groupName) {
        System.out.println("Usage of deprecated Category constructor.");
        this.categoryName = listName;
        this.groupName = groupName;
    }

    public Category(String categoryName, int groupID) {
        this.categoryName = categoryName;
        this.groupID = groupID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        if (categoryID < 0)
        {
            throw new IllegalArgumentException("Category ID cannot be negative, must be positive, >0.");
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

    /**
     * DEPRECATED; use getGroupID instead.
     * @return
     */
    public String getGroupName() {
        System.out.println("Usage of deprecated method, use getGroupID instead.");
        return groupName;
    }

    public void setGroupID(int newID) {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("Category group ID cannot be negative, must be positive, >0.");
        }

        groupID = newID;
    }

    public int getGroupID() {
        return groupID;
    }
}
