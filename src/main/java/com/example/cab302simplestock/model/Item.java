package com.example.cab302simplestock.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
    int itemID;
    String itemName;
    LocalDate purchaseDate;
    double purchasePrice;
    double value;
    String description;
    String categoryName;
    String typeName;

    public Item(String itemName, String purchaseDate, double purchasePrice, double value, String description, String categoryName, String typeName) {
        this.itemName = itemName;
        this.purchaseDate = LocalDate.parse(purchaseDate);
        this.purchasePrice = purchasePrice;
        this.value = value;
        this.description = description;
        this.categoryName = categoryName;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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



}
