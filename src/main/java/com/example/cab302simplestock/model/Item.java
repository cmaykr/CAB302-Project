package com.example.cab302simplestock.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
    int itemID;
    String itemName;
    LocalDate purchaseDate;
    double purchasePrice;
    double quantity;
    String description;
    String categoryName; // FIXME Deprecated, use categoryID instead
    String typeName;  // FIXME Deprecated, use typeID instead
    int categoryID;
    int typeID;
    String location;
    boolean insured;

    /**
     * DEPRECATED, use the other Item constructor with categoryID and typeID instead.
     */
    public Item(String itemName, String purchaseDate, double purchasePrice, double quantity, String description, String categoryName, String typeName, String location, boolean insured) {
        System.out.println("Usage of deprecated Item constructor.");

        this.itemName = itemName;
        this.purchaseDate = LocalDate.parse(purchaseDate);
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.description = description;
        this.categoryName = categoryName;
        this.typeName = typeName;
        this.location = location;
        this.insured = insured;
    }

    public Item(String itemName, String purchaseDate, double purchasePrice, double quantity, String description, int categoryID, int typeID, String location, boolean insured) {
        this.itemName = itemName;
        this.purchaseDate = LocalDate.parse(purchaseDate);
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.description = description;
        this.categoryID = categoryID;
        this.typeID = typeID;
        this.location = location;
        this.insured = insured;
    }

    /**
     * DEPRECATED, use getTypeID instead.
     */
    public String getTypeName() {
        System.out.println("Usage of deprecated function, use getTypeID instead.");
        return typeName;
    }

    /**
     * DEPRECATED, use setTypeID instead.
     */
    public void setTypeName(String typeName) {
        System.out.println("Usage of deprecated function, use setTypeID instead.");
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int newID) {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("Item type ID cannot be negative, must be a positive value, >0.");
        }
        typeID = newID;
    }

    /**
     * DEPRECATED, use getCategoryID instead.
     */
    public String getCategoryName() {
        System.out.println("Usage of deprecated function, use getCategoryID instead.");
        return categoryName;
    }

    /**
     * DEPRECATED, use setCategoryID instead.
     */
    public void setCategoryName(String categoryName) {
        System.out.println("Usage of deprecated function, use setCategoryID instead.");
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int newID) {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("Item category ID cannot be negative, must be a positive value, >0.");
        }
        categoryID = newID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        if (quantity < 0)
        {
            throw new IllegalArgumentException("Item quantity cannot be negative, must be a positive value.");
        }
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        if (purchasePrice < 0)
        {
            throw new IllegalArgumentException("Item purchase price cannot be negative.");
        }
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseDate() {
        return purchaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = LocalDate.parse(purchaseDate);
    }

    public String getName() {
        return itemName;
    }

    public void setName(String itemName)
    {
        if (itemName.isEmpty())
        {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        this.itemName = itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int ID) {
        if (ID <= 0)
        {
            throw new IllegalArgumentException("Item ID cannot be negative, must be positive, >0.");
        }
        itemID = ID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String newLoc) {
        location = newLoc;
    }

    public boolean getInsured() {
        return insured;
    }

    public void setInsured(boolean newInsured) {
        insured = newInsured;
    }
}
