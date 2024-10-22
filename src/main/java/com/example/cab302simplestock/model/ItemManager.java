package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.ITypeDAO;
import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;

import java.util.List;

public class ItemManager implements IItemManager {
    IItemDAO itemDAO;
    ITypeManager typeManager;

    public ItemManager(IItemDAO itemDAO, ITypeManager typeManager) {
        this.itemDAO = itemDAO;
        this.typeManager = typeManager;
    }

    @Override
    public int addItem(Item item) {
        Type type = typeManager.findType(item.getTypeName());
        int typeID = -1;
        if (type == null)
        {
            Type newType = new Type(item.getTypeName());
            typeID = typeManager.addType(newType);
        }
        else
            typeID = type.getTypeID();

        item.setTypeID(typeID);
        itemDAO.addItem(item);
        return 0;
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
}
