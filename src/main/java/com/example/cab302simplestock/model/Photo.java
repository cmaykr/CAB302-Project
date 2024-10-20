package com.example.cab302simplestock.model;

/**
 * Simple model class for representing a photo, with the image name and the item ID the photo is of.
 */
public class Photo {
    int photoID;
    String imageName;
    int itemID;

    /**
     * Constructor that sets the value for image name and item ID.
     * @param imageName The name/path of the image.
     * @param itemID The ID of the item the photo is of.
     */
    public Photo(String imageName, int itemID) {
        this.imageName = imageName;
        this.itemID = itemID;
    }

    /**
     * Gets the ID for the photo model.
     * @return The photo ID.
     */
    public int getPhotoID() {
        return photoID;
    }

    /**
     * Sets a new ID for the photo.
     * @param id The new ID, must be positive, >0.
     * @throws IllegalArgumentException If the new ID is negative or 0.
     */
    public void setPhotoID(int id) throws IllegalArgumentException {
        if (id <= 0)
        {
            throw new IllegalArgumentException("Photo ID cannot be negative, must be a positive value, >0.");
        }
        photoID = id;
    }

    /**
     * Gets the name/path of the image.
     * @return The name/path.
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets a new image name/path.
     * @param imageName The new name/path.
     * @throws IllegalArgumentException If the new name parameter is empty.
     */
    public void setImageName(String imageName) throws IllegalArgumentException {
        if (imageName.isEmpty())
        {
            throw new IllegalArgumentException("Photo image name cannot be empty.");
        }
        this.imageName = imageName;
    }

    /**
     * Gets the ID of the item the photo is of.
     * @return The item ID.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Sets a new ID of the item the photo is of.
     * @param itemID The new item ID, must be positive, >0.
     * @throws IllegalArgumentException If the item ID is negative or 0.
     */
    public void setItemID(int itemID) throws IllegalArgumentException {
        if (itemID <= 0)
        {
            throw new IllegalArgumentException("Photo Item ID cannot be negative, must be a positive value, >0.");
        }
        this.itemID = itemID;
    }
}
