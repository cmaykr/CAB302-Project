package com.example.cab302simplestock.model;

import java.util.List;

public interface IItemDAO {

    public void addItem(Item item);

    public void updateItem(Item item);

    public void deleteItem(Item item);

    public List<Item> getAllItems();

    public Item getItem(); // FIXME Is this method needed?
}
