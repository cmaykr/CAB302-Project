package com.example.cab302simplestock.model.Managers;

import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.Item;
import com.example.cab302simplestock.model.ManagerInterfaces.ICategoryManager;
import com.example.cab302simplestock.model.ManagerInterfaces.IItemManager;
import com.example.cab302simplestock.model.ManagerInterfaces.ITypeManager;
import com.example.cab302simplestock.model.Type;

import java.util.List;

/**
 * Implementation of the itemManager interface using a itemDAO for the managing of items.
 * Uses dependency injection to get the item DAO, type and category manager.
 */
public class ItemManager implements IItemManager {
    IItemDAO itemDAO;
    ITypeManager typeManager;
    ICategoryManager categoryManager;

    /**
     * Constructs an item manager that uses a specified item DAO, type and category manager which are injected.
     * @param itemDAO The item DAO to use.
     * @param typeManager The type manager to use.
     * @param categoryManager The category manager to use.
     */
    public ItemManager(IItemDAO itemDAO, ITypeManager typeManager, ICategoryManager categoryManager) {
        this.itemDAO = itemDAO;
        this.typeManager = typeManager;
        this.categoryManager = categoryManager;
    }

    /**
     * Adds a new item to the database for a specific group, takes the name of the items type and category for easier implementation of adding the correct type and category for the item.
     * If the type doesn't exist it is created and added to the database.
     * @param item The item to add to the database.
     * @param typeName The name of the type the item is of.
     * @param categoryName The name of the category the item should be added to.
     * @param groupID The ID of the group the item should be added to.
     * @return The ID of the newly added item.
     */
    @Override
    public int addItem(Item item, String typeName, String categoryName, int groupID) {
        Type type = typeManager.getType(typeName);
        int typeID = -1;
        if (type == null)
        {
            // Create the type as it doesn't exist.
            Type newType = new Type(typeName);
            typeID = typeManager.addType(newType);
        }
        else
            typeID = type.getTypeID();
        Category category = categoryManager.findCategoryInGroupByName(categoryName, groupID);

        item.setTypeID(typeID);
        item.setCategoryID(category.getCategoryID());
        return itemDAO.addItem(item);
    }

    /**
     * CRUD method to update an item in the database
     * The ID value for the item in the database is used to find which item to update.
     * @param item The item to update.
     */
    @Override
    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    /**
     * CRUD method to delete an item in the database
     * The ID value of the item is used to find the item to delete in the database.
     * @param item The item to delete, only needs the values that are used to find which item to delete.
     */
    @Override
    public void deleteItem(Item item) {
        itemDAO.deleteItem(item);
    }

    /**
     * Gets all items that exist in the database.
     * @return A list of all items.
     */
    @Override
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    /**
     * Searches and finds an item in the database based on the items ID value.
     * @param itemId The ID of the item to search for.
     * @return The item if found.
     */
    @Override
    public Item findItemByID(int itemId) {
        return itemDAO.findItemByID(itemId);
    }

    /**
     * Searches for every item in a category that includes the name or part of the name in the parameter.
     * @param itemName The name or part of name for the items to search for.
     * @param groupID The ID of the group the item should be searched in.
     * @return A list of all items with identical or part of the inputted name.
     */
    @Override
    public List<Item> searchItemsByNameInGroup(String itemName, int groupID) {
        List<Category> categories = categoryManager.getCategoriesInGroup(groupID);
        // Only one category exists per group.
        return itemDAO.getItemByNameInCategory(itemName, categories.getFirst().getCategoryID());
    }
}
