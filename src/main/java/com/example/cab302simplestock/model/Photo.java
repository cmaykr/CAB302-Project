package com.example.cab302simplestock.model;

public class Photo {
    int photoID;
    String imageName;
    int itemID;

    public Photo(String imageName, int itemID) {
        this.imageName = imageName;
        this.itemID = itemID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int id) {
        if (id <= 0)
        {
            throw new IllegalArgumentException("Photo ID cannot be negative, must be a positive value, >0.");
        }
        photoID = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        if (imageName.isEmpty())
        {
            throw new IllegalArgumentException("Photo image name cannot be empty.");
        }
        this.imageName = imageName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        if (itemID <= 0)
        {
            throw new IllegalArgumentException("Photo Item ID cannot be negative, must be a positive value, >0.");
        }
        this.itemID = itemID;
    }
}
