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

public class SqliteCategoryDAO implements ICategoryDAO {
    Connection connection;

    public SqliteCategoryDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

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

    @Override
    public void addCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO category (categoryName, groupID) VALUES (?, ?)");
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getGroupID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
