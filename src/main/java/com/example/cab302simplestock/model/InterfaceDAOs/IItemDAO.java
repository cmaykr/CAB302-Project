package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Item;

import java.util.List;

public interface IItemDAO {

    public void addItem(Item item);

    public void updateItem(Item item);

    public void deleteItem(Item item);

    public List<Item> getAllItems();
}
