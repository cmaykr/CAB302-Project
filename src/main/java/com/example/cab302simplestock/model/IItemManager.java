package com.example.cab302simplestock.model;

import java.util.List;

/**
 * Interface for a item manager defining CRUD methods for Items and some extra specific methods for searching and finding.
 */
public interface IItemManager {

    /**
     * Adds a new item to the database for a specific group, takes the name of the items type and category for easier implementation of adding the correct type and category for the item.
     * If the type or category doesn't exist this method should create them.
     * @param item The item to add to the database.
     * @param typeName The name of the type the item is of.
     * @param categoryName The name of the category the item should be added to.
     * @param groupID The ID of the group the item should be added to.
     * @return The ID of the newly added item.
     */
    int addItem(Item item, String typeName, String categoryName, int groupID);

    /**
     * CRUD method to update an item in the database
     * Which item that should be updated can be decided for any of the values, decided by the implementation.
     * @param item The item to update.
     */
    void updateItem(Item item);

    /**
     * CRUD method to delete an item in the database
     * Which value that's used to find the item is implementation specific.
     * @param item The item to delete, only needs the values that are used to find which item to delete.
     */
    void deleteItem(Item item);

    /**
     * Gets all items that exist in the database.
     * @return A list of all items.
     */
    List<Item> getAllItems();

    /**
     * Searches and finds an item in the database based on the items ID value.
     * @param itemId The ID of the item to search for.
     * @return The item if found.
     */
    Item findItemByID(int itemId);

    /**
     * Searches for every item in a category that includes the name or part of the name in the parameter.
     *
     * @param itemName The name or part of name for the items to search for.
     * @param groupID The ID of the group the item should be searched in.
     * @return A list of all items with identical or part of the inputted name.
     */
    List<Item> searchItemsByNameInGroup(String itemName, int groupID);
}
