package com.example.cab302simplestock.model;

import java.util.List;

public interface IItemManager {

    int addItem(Item item, String typeName, String categoryName, int groupID);

    void updateItem(Item item);

    void deleteItem(Item item);

    List<Item> getAllItems();

    Item findItemByID(int itemId);

    List<Item> searchItemsByNameInCategory(String itemName, int groupID);
}
