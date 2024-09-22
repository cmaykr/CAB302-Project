package com.example.cab302simplestock.model;

import com.example.cab302simplestock.model.Photo;
import java.util.List;

public interface IPhotoDAO {
    public void addPhoto(Photo photo);

    public void updatePhoto(Photo photo);

    public void deletePhoto(Photo photo);

    public List<Photo> getAllPhotos(Photo photo);
}