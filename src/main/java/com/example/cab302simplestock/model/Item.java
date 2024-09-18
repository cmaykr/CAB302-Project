package com.example.cab302simplestock.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Item {
    int itemID;
    String itemName;
    LocalDate purchaseDate;
    double purchasePrice;
    double value;
    String description;
    String listName;
    String typeName;

    public Item(String itemName, String purchaseDate, double purchasePrice, double value, String description, String listName, String typeName) {
        this.itemName = itemName;
        this.purchaseDate = LocalDate.parse(purchaseDate);
        this.purchasePrice = purchasePrice;
        this.value = value;
        this.description = description;
        this.listName = listName;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
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

    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public int getID() {
        return itemID;
    }

    /*
    name
    category
    description
    purchasePrice
    purchaseDate
    value
    photos
     */
}
