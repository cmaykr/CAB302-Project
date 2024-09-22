package com.example.cab302simplestock.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteItemDAO implements IItemDAO{
    private Connection connection;

    public SqliteItemDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable()
    {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS items ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "purchaseDate DATE NOT NULL,"
                    + "purchasePrice REAL NOT NULL,"
                    + "value REAL NOT NULL,"
                    + "description VARCHAR NOT NULL,"
                    + "categoryName VARCHAR NOT NULL,"
                    + "typeName VARCHAR NOT NULL,"
                    + "location VARCHAR NOT NULL,"
                    + "insured INTEGER NOT NULL,"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO items (name, purchaseDate, purchasePrice, value, " +
                    "description, categoryName, typeName, location, insured VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, item.getName());
            statement.setDate(2, Date.valueOf(item.getPurchaseDate()));
            statement.setDouble(3, item.getPurchasePrice());
            statement.setDouble(4, item.getValue());
            statement.setString(5, item.getDescription());
            statement.setString(6, item.getCategoryName());
            statement.setString(7, item.getTypeName());
            statement.setString(8, item.getLocation());
            statement.setBoolean(9, item.getInsured());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE items SET name = ?, purchasePrice = ?, purchaseDate = ?, " +
                    "value = ?, description = ?, categoryName = ?, typeName = ?, location = ?, insured = ? WHERE id = ?");
            statement.setString(1, item.getName());
            statement.setDate(2, Date.valueOf(item.getPurchaseDate()));
            statement.setDouble(3, item.getPurchasePrice());
            statement.setDouble(4, item.getValue());
            statement.setString(5, item.getDescription());
            statement.setString(6, item.getCategoryName());
            statement.setString(7, item.getTypeName());
            statement.setString(8, item.getLocation());
            statement.setBoolean(9, item.getInsured());
            statement.setInt(10, item.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE id = ?");
            statement.setInt(1, item.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM items";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date purchaseDate = resultSet.getDate("purchaseDate");
                double purchasacePrice = resultSet.getDouble("purchasePrice");
                double value = resultSet.getDouble("value");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("categoryName");
                String typeName = resultSet.getString("typeName");
                String location = resultSet.getString("location");
                boolean insured = resultSet.getBoolean("insured");
                Item item = new Item(name, purchaseDate.toString(), purchasacePrice, value, description, categoryName, typeName, location, insured);
                item.setID(id);
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
