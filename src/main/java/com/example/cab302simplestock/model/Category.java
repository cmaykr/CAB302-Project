package com.example.cab302simplestock.model;

/**
 * Simple model class for representing a category with a name and the group it's owned by.
 */
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

    /**
     * Main constructor for the class.
     * @param categoryName The name of the category.
     * @param groupID The ID of the group the category is owned by.
     */
    public Category(String categoryName, int groupID) {
        this.categoryName = categoryName;
        this.groupID = groupID;
    }

    /**
     * Gets the ID of the category.
     * @return The ID of type integer.
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Sets a new ID for the model category.
     * @param categoryID The new ID, must be >0.
     * @throws IllegalArgumentException If the new ID is an invalid value.
     */
    public void setCategoryID(int categoryID) throws IllegalArgumentException {
        if (categoryID < 0)
        {
            throw new IllegalArgumentException("Category ID cannot be negative, must be positive, >0.");
        }
        this.categoryID = categoryID;
    }

    /**
     * Gets the name of the category.
     * @return The category name of type String.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets a new category name.
     * @param categoryName The new category name.
     * @throws IllegalArgumentException If the categoryName parameter is empty.
     */
    public void setCategoryName(String categoryName) throws IllegalArgumentException {
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

    /**
     * Sets a new group the category is owned by.
     * @param newID The new group ID.
     * @throws IllegalArgumentException If the new ID parameter is invalid.
     */
    public void setGroupID(int newID) throws IllegalArgumentException {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("Category group ID cannot be negative, must be positive, >0.");
        }

        groupID = newID;
    }

    /**
     * Gets the group's ID the category is owned by.
     * @return The group ID of type integer.
     */
    public int getGroupID() {
        return groupID;
    }
}
