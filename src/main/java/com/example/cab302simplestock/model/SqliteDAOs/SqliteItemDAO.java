package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.Item;
import com.example.cab302simplestock.model.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteItemDAO implements IItemDAO {
    private Connection connection;

    public SqliteItemDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable()
    {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS item ("
                    + "itemID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "purchaseDate DATE NOT NULL,"
                    + "purchasePrice REAL NOT NULL,"
                    + "quantity REAL NOT NULL,"
                    + "description VARCHAR,"
                    + "categoryID VARCHAR NOT NULL,"
                    + "typeID VARCHAR NOT NULL,"
                    + "location VARCHAR,"
                    + "insured INTEGER NOT NULL,"
                    + "FOREIGN KEY (categoryID) REFERENCES category(categoryID),"
                    + "FOREIGN KEY (typeID) REFERENCES type(typeID)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO item (name, purchaseDate, purchasePrice, quantity, "
                    + "description, categoryID, typeID, location, insured) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, item.getName());
            statement.setDate(2, Date.valueOf(item.getPurchaseDate()));
            statement.setDouble(3, item.getPurchasePrice());
            statement.setDouble(4, item.getQuantity());
            statement.setString(5, item.getDescription());
            statement.setInt(6, item.getCategoryID());
            statement.setInt(7, item.getTypeID());
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
            PreparedStatement statement = connection.prepareStatement("UPDATE item SET name = ?, purchasePrice = ?, purchaseDate = ?, " +
                    "quantity = ?, description = ?, categoryID = ?, typeID = ?, location = ?, insured = ? WHERE itemID = ?");
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPurchasePrice());
            statement.setDate(3, Date.valueOf(item.getPurchaseDate()));
            statement.setDouble(4, item.getQuantity());
            statement.setString(5, item.getDescription());
            statement.setInt(6, item.getCategoryID());
            statement.setInt(7, item.getTypeID());
            statement.setString(8, item.getLocation());
            statement.setBoolean(9, item.getInsured());
            statement.setInt(10, item.getItemID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(Item item) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM item WHERE itemID = ?");
            statement.setInt(1, item.getItemID());
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
            String query = "SELECT * FROM item";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("itemID");
                String name = resultSet.getString("name");
                Date purchaseDate = resultSet.getDate("purchaseDate");
                double purchasePrice = resultSet.getDouble("purchasePrice");
                double quantity = resultSet.getDouble("quantity");
                String description = resultSet.getString("description");
                int categoryID = resultSet.getInt("categoryID");
                int typeID = resultSet.getInt("typeID");
                String location = resultSet.getString("location");
                boolean insured = resultSet.getBoolean("insured");
                Item item = new Item(name, purchaseDate.toString(), purchasePrice, quantity, description, categoryID, typeID, location, insured);
                item.setItemID(id);
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
