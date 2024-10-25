package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IPhotoDAO;
import com.example.cab302simplestock.model.Photo;
import com.example.cab302simplestock.model.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Sqlite implementation of the Photo DAO interface.
 */
public class SqlitePhotoDAO implements IPhotoDAO {
    Connection connection;

    /**
     * Connects to the Sqlite database and creates the photo table, if it doesn't exist.
     */
    public SqlitePhotoDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates a Sqlite database table for photo.
     * The columns are photo ID, name, ID of item it's of which references an item, name + itemID are unique.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS photo ("
                    + "photoID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "itemID INTEGER NOT NULL,"
                    + "UNIQUE(name, itemID),"
                    + "FOREIGN KEY (itemID) REFERENCES item(itemID)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a photo to the Sqlite database. The photo ID is set automatically by the database.
     * @param photo The photo that should be added to the database.
     */
    @Override
    public int addPhoto(Photo photo) {
        int photoID = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO photo (name, itemID) VALUES (?, ?)");
            statement.setString(1, photo.getImageName());
            statement.setInt(2, photo.getItemID());
            statement.executeUpdate();

            // Retrieve the last inserted groupID
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            if (rs.next()) {
                photoID = rs.getInt(1);  // Retrieve the generated groupID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return photoID;
    }

    /**
     * Updates a photo that already exists in the database. The photo is chosen by its ID value.
     * If no photo with that ID exists nothing is updated in the database.
     * @param photo The photo that should be updated.
     */
    @Override
    public void updatePhoto(Photo photo) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE photo SET name = ?, itemID = ? WHERE photoID = ?");
            statement.setString(1, photo.getImageName());
            statement.setInt(2, photo.getItemID());
            statement.setInt(3, photo.getPhotoID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a photo in the Sqlite database, looks for the photo with the same ID as the parameter one. No other checks other than the ID is made.
     * @param photo The photo to be deleted. The ID value should be set to the ID value for the photo to be deleted.
     */
    @Override
    public void deletePhoto(Photo photo) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM photo WHERE photoID = ?");
            statement.setInt(1, photo.getPhotoID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets every photo stored in the database, does no checks for the photos.
     * @return A list of all photos in the Sqlite database.
     */
    @Override
    public List<Photo> getAllPhotos() {
        List<Photo> photos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT rowid, * FROM photo";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int photoID = resultSet.getInt("photoID");
                String imageName = resultSet.getString("name");
                int itemID = resultSet.getInt("itemID");
                Photo photo = new Photo(imageName, itemID);
                photo.setPhotoID(photoID);
                photos.add(photo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return photos;
    }
}
