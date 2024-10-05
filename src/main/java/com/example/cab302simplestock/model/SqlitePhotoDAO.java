package com.example.cab302simplestock.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlitePhotoDAO implements IPhotoDAO {
    Connection connection;

    public SqlitePhotoDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS photo ("
                    + "name VARCHAR NOT NULL,"
                    + "itemID INTEGER NOT NULL,"
                    + "PRIMARY KEY (name, itemID),"
                    + "FOREIGN KEY (itemID) REFERENCES item(id)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPhoto(Photo photo) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO photo (name, itemID) VALUES (?, ?)");
            statement.setString(1, photo.getImageName());
            statement.setInt(2, photo.getItemID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePhoto(Photo photo) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE photo SET name = ?, itemID = ? WHERE rowid = ?");
            statement.setString(1, photo.getImageName());
            statement.setInt(2, photo.getItemID());
            statement.setInt(5, photo.getPhotoID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePhoto(Photo photo) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM photo WHERE rowid = ?");
            statement.setInt(1, photo.getPhotoID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Photo> getAllPhotos() {
        List<Photo> photos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT rowid, * FROM photo";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String imageName = resultSet.getString("name");
                int itemID = resultSet.getInt("itemID");
                Photo photo = new Photo(imageName, itemID);
                photo.setPhotoID(resultSet.getInt("rowid"));
                photos.add(photo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photos;
    }
}
