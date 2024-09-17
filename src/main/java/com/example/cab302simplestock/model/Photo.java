package com.example.cab302simplestock.model;

public class Photo {
    String imageName;
    String itemID;

    public Photo(String imageName, User user) {
        this.imageName = imageName;
        this.itemID = user.getID();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(User newUser) {
        this.itemID = newUser.getID();
    }
}
