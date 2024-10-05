package com.example.cab302simplestock.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteGroupDAO implements IGroupDAO{
    private Connection connection;

    public SqliteGroupDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS groupDB ("
                    + "name VARCHAR PRIMARY KEY,"
                    + "ownerID INTEGER NOT NULL,"
                    + "FOREIGN KEY (ownerID) REFERENCES user(id)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void updateGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE groupDB SET ownerID = ? WHERE name = ?");

            statement.setInt(1, group.getOwnerID());
            statement.setString(2, group.getGroupName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM groupDB WHERE name = ?");

            statement.setString(1, group.getGroupName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM groupDB";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int ownerID = resultSet.getInt("ownerID");
                Group group = new Group(name, ownerID);
                groups.add(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }
}
