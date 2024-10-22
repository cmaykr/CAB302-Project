package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.Category;
import com.example.cab302simplestock.model.InterfaceDAOs.ICategoryDAO;
import com.example.cab302simplestock.model.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Sqlite implementation of the category DAO interface.
 */
public class SqliteCategoryDAO implements ICategoryDAO {
    Connection connection;

    /**
     * Connects to the sqlite database and creates the category table, if it doesn't exist.
     */
    public SqliteCategoryDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates a Sqlite table for category with ID, name, the group ID.
     * Sets group ID to reference the GroupDB table ID column.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS category ("
                    + "categoryID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "categoryName VARCHAR NOT NULL,"
                    + "groupID VARCHAR NOT NULL,"
                    + "UNIQUE(categoryName, groupID),"
                    + "FOREIGN KEY (groupID) REFERENCES groupDB(groupID)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a category to the Sqlite database. The category ID is set automatically by the database.
     * @param category The category that should be added to the database.
     */
    @Override
    public int addCategory(Category category) {
        int categoryID = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO category (categoryName, groupID) VALUES (?, ?)");
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getGroupID());
            statement.executeUpdate();

            // Retrieve the last inserted groupID
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            if (rs.next()) {
                categoryID = rs.getInt(1);  // Retrieve the generated groupID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryID;
    }

    /**
     * Updates a category that already exists in the database. The category is chosen by its ID value.
     * If no category with that ID exists nothing is updated in the database.
     * @param category The category that should be updated.
     */
    @Override
    public void updateCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE category SET groupID = ?, categoryName = ? WHERE categoryID = ?");
            statement.setInt(1, category.getGroupID());
            statement.setString(2, category.getCategoryName());
            statement.setInt(3, category.getCategoryID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a category in the Sqlite database, looks for the category with the same ID as the parameter one. No other checks other than the ID is made.
     * @param category The category to be deleted. The ID value should be set to the ID value for the category to be deleted.
     */
    @Override
    public void deleteCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM category WHERE categoryID = ?");
            statement.setInt(1, category.getCategoryID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets every category stored in the database, does no checks for the categories.
     * @return A list of all categories in the Sqlite database.
     */
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM category";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int categoryID = resultSet.getInt("categoryID");
                String categoryName = resultSet.getString("categoryName");
                int groupID = resultSet.getInt("groupID");
                Category category = new Category(categoryName, groupID);
                category.setCategoryID(categoryID);
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
