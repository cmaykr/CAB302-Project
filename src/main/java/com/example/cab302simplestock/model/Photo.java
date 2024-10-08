package com.example.cab302simplestock.model;

public class Photo {
    int photoID;
    String imageName;
    int itemID;

    public Photo(String imageName, Item item) {
        this.imageName = imageName;
        this.itemID = item.getID();
    }

    public Photo(String imageName, int itemID) {
        this.imageName = imageName;
        this.itemID = itemID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int id) {
        photoID = id;
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
