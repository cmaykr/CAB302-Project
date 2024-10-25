package com.example.cab302simplestock.model.InterfaceDAOs;

import com.example.cab302simplestock.model.Photo;
import java.util.List;

/**
 * CRUD interface for interacting with photos in a database.
 */
public interface IPhotoDAO {
    /**
     * Adds a new photo to a database. The ID should be set by the database.
     * @param photo The photo to add.
     */
    int addPhoto(Photo photo);

    /**
     * Updates a photo in the database, the photo ID should be used to find which photo to update.
     * @param photo The photo to update, the ID should be set.
     */
    void updatePhoto(Photo photo);

    /**
     * Deletes a photo in a database, should be chosen by the photo ID.
     * @param photo The item to delete, the ID value should be set.
     */
    void deletePhoto(Photo photo);

    /**
     * Gets all photos in a database, should set all values in the Photo model.
     * @return A list of photos.
     */
    List<Photo> getAllPhotos();
}
