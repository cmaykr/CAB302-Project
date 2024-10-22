package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Item;

import java.util.List;

/**
 * CRUD interface for interacting with items in a database.
 */
public interface IItemDAO {

    /**
     * Adds a new item to a database. The ID should be set by the database.
     * @param item The item to add.
     */
    int addItem(Item item);

    /**
     * Updates an item in the database, the item ID should be used to find which item to update.
     * @param item The item to update, the ID should be set.
     */
    void updateItem(Item item);

    /**
     * Deletes an item in a database, should be chosen by the item ID.
     * @param item The item to delete, the ID value should be set.
     */
    void deleteItem(Item item);

    /**
     * Gets all items in a database, should set all values in the Group model.
     * @return A list of items.
     */
    List<Item> getAllItems();

    Item findItemByID(int itemId);
}
