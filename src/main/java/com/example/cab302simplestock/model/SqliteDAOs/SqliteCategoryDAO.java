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
                    + "name VARCHAR NOT NULL,"
                    + "groupName VARCHAR NOT NULL,"
                    + "PRIMARY KEY (categoryName, groupName),"
                    + "FOREIGN KEY (groupName) REFERENCES groupDB(name)"
                    + "UNIQUE(name, groupName)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO category (name, groupName) VALUES (?, ?)");
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getGroupName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE category SET groupName = ? WHERE name = ?");
            statement.setString(1, category.getGroupName());
            statement.setString(2, category.getCategoryName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM category WHERE name = ?");
            statement.setString(1, category.getCategoryName());
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
                String categoryName = resultSet.getString("name");
                String groupName = resultSet.getString("groupName");
                Category category = new Category(categoryName, groupName);
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
