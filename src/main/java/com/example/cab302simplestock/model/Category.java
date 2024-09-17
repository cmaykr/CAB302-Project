package com.example.cab302simplestock.model;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private java.util.List<Item> items;

    // Constructor
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.items = new ArrayList<>();
    }

    // Functions to add and remove items
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    // Das getters und das setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public java.util.List<Item> getItems() { return items; }

}
