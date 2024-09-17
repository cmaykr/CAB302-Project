package com.example.cab302simplestock.model;

public class Item {
    private int id;
    private String name;
    private int category;
    private String description;
    private double purchasePrice;
    private String purchaseDate;
    private int quantity;

    // Difference between value and purchase price? Is this for depreciation?
    // private int value;

    // How do we want to display images? Is this something we store in the database or locally?
    // private --- image;

    // Constructor
    public Item(int id, String name, int category, String description, double purchasePrice, String purchaseDate, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
    }

    // Das getters und das setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCategory() { return category; }
    public void setCategory(int category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }


}
