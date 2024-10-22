package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IItemDAO;
import com.example.cab302simplestock.model.Item;
import com.example.cab302simplestock.model.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Sqlite implementation of the Item DAO interface.
 */
public class SqliteItemDAO implements IItemDAO {
    private Connection connection;

    /**
     * Connects to the Sqlite database and creates the item table, if it doesn't exist.
     */
    public SqliteItemDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates a Sqlite database table for item.
     * The columns are item ID, name, purchase date, purchase price, quantity, description, category ID, type ID, location and if it's insured.
     */
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

    /**
     * Adds an item to the Sqlite database. The item ID is set automatically by the database.
     * @param item The item that should be added to the database.
     */
    @Override
    public int addItem(Item item) {
        int itemID = -1;
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

            // Retrieve the last inserted groupID
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            if (rs.next()) {
                itemID = rs.getInt(1);  // Retrieve the generated groupID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemID;
    }

    /**
     * Updates an item that already exists in the database. The item is chosen by its ID value.
     * If no item with that ID exists nothing is updated in the database.
     * @param item The item that should be updated.
     */
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

    /**
     * Deletes an item in the Sqlite database, looks for the item with the same ID as the parameter one. No other checks other than the ID is made.
     * @param item The item to be deleted. The ID value should be set to the ID value for the item to be deleted.
     */
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

    /**
     * Gets every item stored in the database, does no checks for the items.
     * @return A list of all items in the Sqlite database.
     */
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

    @Override
    public Item findItemByID(int itemId) {
        try {
            String query = "SELECT * FROM item WHERE itemID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
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
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getItemByNameInCategory(String itemName, int categoryID) {
        List<Item> items = new ArrayList<>();
        try {
            String query = "SELECT * FROM item WHERE name = ? AND categoryID";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, itemName);
            statement.setInt(2, categoryID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("itemID");
                String name = resultSet.getString("name");
                Date purchaseDate = resultSet.getDate("purchaseDate");
                double purchasePrice = resultSet.getDouble("purchasePrice");
                double quantity = resultSet.getDouble("quantity");
                String description = resultSet.getString("description");
                int catID = resultSet.getInt("categoryID");
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
