package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.ITypeDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;

import java.util.List;

public class ItemManager implements IItemManager {
    IItemDAO itemDAO;
    ITypeManager typeManager;
    ICategoryManager categoryManager;

    public ItemManager(IItemDAO itemDAO, ITypeManager typeManager, ICategoryManager categoryManager) {
        this.itemDAO = itemDAO;
        this.typeManager = typeManager;
        this.categoryManager = categoryManager;
    }

    @Override
    public int addItem(Item item, String typeName, String categoryName, int groupID) {
        Type type = typeManager.findType(typeName);
        int typeID = -1;
        if (type == null)
        {
            // Create the type as it doesn't exist.
            Type newType = new Type(typeName);
            typeID = typeManager.addType(newType);
        }
        else
            typeID = type.getTypeID();
        System.out.println(categoryName);
        System.out.println(groupID);
        Category category = categoryManager.findCategoryInGroupByName(categoryName, groupID);

        System.out.println(categoryManager.getAllCategories().size());

        item.setTypeID(typeID);
        item.setCategoryID(category.getCategoryID());
        return itemDAO.addItem(item);
    }

    @Override
    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemDAO.deleteItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    @Override
    public Item findItemByID(int itemId) {
        return itemDAO.findItemByID(itemId);
    }

    @Override
    public List<Item> searchItemsByNameInGroup(String itemName, int categoryID) {
        return itemDAO.getItemByNameInCategory(itemName, categoryID);
    }
}
