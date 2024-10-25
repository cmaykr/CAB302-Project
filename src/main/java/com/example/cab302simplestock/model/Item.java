package com.example.cab302simplestock.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Simple model class for representing an Item with name, purchase date, purchase price, quantity, description, which category it's in, which type it's of, location and if it's insured.
 */
public class Item {
    private int itemID;
    private String itemName;
    private LocalDate purchaseDate;
    private double purchasePrice;
    private double quantity;
    private String description;
    private int categoryID;
    private int typeID;
    private String location;
    private boolean insured;

    /**
     * Constructor for the Item class. Sets every variable with the value in their respective parameters.
     * @param itemName The name of the item.
     * @param purchaseDate The date of purchase, is parsed to type LocalDate.
     * @param purchasePrice The purchase price of the item.
     * @param quantity How many of the item exists.
     * @param description The description of the item.
     * @param categoryID The ID of the category the item exists in.
     * @param typeID The ID of the type the item is of.
     * @param location Where the item is located.
     * @param insured If the item is insured or not.
     */
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
     * Gets the ID for the type of the item.
     * @return The ID for the type.
     */
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
     * Gets the ID of the category the Item is in.
     * @return The ID of the category.
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Sets a new category ID for the item, the ID must be positive, >0.
     * @param newID The new ID for the category.
     * @throws IllegalArgumentException If the ID is negative or 0.
     */
    public void setCategoryID(int newID) throws IllegalArgumentException {
        if (newID <= 0)
        {
            throw new IllegalArgumentException("Item category ID cannot be negative, must be a positive value, >0.");
        }
        categoryID = newID;
    }

    /**
     * Gets the description of the Item.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets a new description for the item.
     * @param description The new description, can be any valid value for String type.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the quantity for the item.
     * @return The quantity.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets a new quantity for the item, must be positive, >0.
     * @param quantity The new quantity.
     * @throws IllegalArgumentException If the new quantity is negative or 0.
     */
    public void setQuantity(double quantity) throws IllegalArgumentException {
        if (quantity < 0)
        {
            throw new IllegalArgumentException("Item quantity cannot be negative, must be a positive value.");
        }
        this.quantity = quantity;
    }

    /**
     * Gets the purchase price of the item.
     * @return The purchase price.
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Sets a new purchase price for the item, must be positive, >0.
     * @param purchasePrice The new purchase price.
     * @throws IllegalArgumentException If the purchase price parameter is negative or 0.
     */
    public void setPurchasePrice(double purchasePrice) throws IllegalArgumentException {
        if (purchasePrice < 0)
        {
            throw new IllegalArgumentException("Item purchase price cannot be negative.");
        }
        this.purchasePrice = purchasePrice;
    }

    /**
     * Gets the purchase date.
     * @return The purchase date.
     */
    public String getPurchaseDate() {
        return purchaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Sets a new purchase date for the item.
     * @param purchaseDate The new purchase date in String format.
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = LocalDate.parse(purchaseDate);
    }

    /**
     * Gets the name of the item.
     * @return The name of the item.
     */
    public String getName() {
        return itemName;
    }

    /**
     * Sets a new name for the item.
     * @param itemName The new name.
     * @throws IllegalArgumentException If the new name is invalid, e.g empty.
     */
    public void setName(String itemName) throws IllegalArgumentException {
        if (itemName.isEmpty())
        {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        this.itemName = itemName;
    }

    /**
     * Gets the ID of the item.
     * @return The ID.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Sets a new ID for the item.
     * @param ID The new ID.
     * @throws IllegalArgumentException If the ID parameter is negative or 0.
     */
    public void setItemID(int ID) throws IllegalArgumentException {
        if (ID <= 0)
        {
            throw new IllegalArgumentException("Item ID cannot be negative, must be positive, >0.");
        }
        itemID = ID;
    }

    /**
     * Gets the location of the item.
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets a new location for the item.
     * @param newLoc The new location, can be any valid value of String type.
     */
    public void setLocation(String newLoc) {
        location = newLoc;
    }

    /**
     * Gets the insured variable of item.
     * @return True or False if the item is insured.
     */
    public boolean getInsured() {
        return insured;
    }

    /**
     * Sets if the item is insured or not.
     * @param newInsured The value for the insured variable.
     */
    public void setInsured(boolean newInsured) {
        insured = newInsured;
    }
}
