package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.Group;
import com.example.cab302simplestock.model.InterfaceDAOs.IGroupDAO;
import com.example.cab302simplestock.model.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Sqlite implementation of the Group DAO interface.
 */
public class SqliteGroupDAO implements IGroupDAO {
    private Connection connection;

    /**
     * Connects to the Sqlite database and creates the Group table, if it doesn't exist.
     */
    public SqliteGroupDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates a Sqlite database table for group, is named groupDB because group is protected in Sqlite.
     * The columns are group ID, name, the user ID that owns the group and references the User table.
     * The group name is set to be unique.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS groupDB ("
                    + "groupID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR,"
                    + "ownerID INTEGER NOT NULL,"
                    + "FOREIGN KEY (ownerID) REFERENCES user(userID),"
                    + "UNIQUE(name)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a group to the Sqlite database. The group ID is set automatically by the database.
     * @param group The group that should be added to the database.
     */
    @Override
    public void addGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO groupDB (name, ownerID) VALUES (?, ?)");

            statement.setString(1, group.getGroupName());
            statement.setInt(2, group.getOwnerID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a group that already exists in the database. The group is chosen by its ID value.
     * If no group with that ID exists nothing is updated in the database.
     * @param group The group that should be updated.
     */
    @Override
    public void updateGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE groupDB SET ownerID = ?, name = ? WHERE groupID = ?");

            statement.setInt(1, group.getOwnerID());
            statement.setString(2, group.getGroupName());
            statement.setString(3, group.getGroupName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a group in the Sqlite database, looks for the group with the same ID as the parameter one. No other checks other than the ID is made.
     * @param group The group to be deleted. The ID value should be set to the ID value for the group to be deleted.
     */
    @Override
    public void deleteGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM groupDB WHERE groupID = ?");

            statement.setInt(1, group.getGroupID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets every group stored in the database, does no checks for the groups.
     * @return A list of all groups in the Sqlite database.
     */
    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM groupDB";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int groupID = resultSet.getInt("groupID");
                String name = resultSet.getString("name");
                int ownerID = resultSet.getInt("ownerID");
                Group group = new Group(name, ownerID);
                group.setGroupID(groupID);
                groups.add(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }
}
