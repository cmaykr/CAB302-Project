package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.ITypeDAO;
import com.example.cab302simplestock.model.SqliteConnection;
import com.example.cab302simplestock.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Sqlite implementation of the Type DAO interface.
 */
public class SqliteTypeDAO implements ITypeDAO {
    Connection connection;

    /**
     * Connects to the Sqlite database and creates the type table, if it doesn't exist.
     */
    public SqliteTypeDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates a Sqlite database table for type.
     * The columns are type ID and its name.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS type ("
                    + "typeID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a type to the Sqlite database. The type ID is set automatically by the database.
     * @param type The type that should be added to the database.
     */
    @Override
    public int addType(Type type) {
        int typeID = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO type (name) VALUES (?)");
            statement.setString(1, type.getName());
            statement.executeUpdate();

            // Retrieve the last inserted groupID
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            if (rs.next()) {
                typeID = rs.getInt(1);  // Retrieve the generated groupID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return typeID;
    }

    /**
     * Updates a type that already exists in the database. The type is chosen by its ID value.
     * If no type with that ID exists nothing is updated in the database.
     * @param type The type that should be updated.
     */
    @Override
    public void updateType(Type type) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE type SET name = ? WHERE typeID = ?");
            statement.setString(1, type.getName());
            statement.setInt(2, type.getTypeID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a type in the Sqlite database, looks for the type with the same ID as the parameter one. No other checks other than the ID is made.
     * @param type The type to be deleted. The ID value should be set to the ID value for the type to be deleted.
     */
    @Override
    public void deleteType(Type type) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM type WHERE typeID = ?");
            statement.setInt(1, type.getTypeID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets every type stored in the database, does no checks for the types.
     * @return A list of all types in the Sqlite database.
     */
    @Override
    public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM type";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int typeID = resultSet.getInt("typeID");
                String typeName = resultSet.getString("name");
                Type type = new Type(typeName);
                type.setTypeID(typeID);
                types.add(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }
}
