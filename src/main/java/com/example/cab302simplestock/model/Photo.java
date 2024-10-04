package com.example.cab302simplestock.model;

public class Photo {
    String imageName;
    int itemID;

    public Photo(String imageName, Item item) {
        this.imageName = imageName;
        this.itemID = item.getID();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(Item newItem) {
        this.itemID = newItem.getID();
    }
}
