package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IViewUserDAO;
import com.example.cab302simplestock.model.SqliteConnection;
import com.example.cab302simplestock.model.ViewUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteViewUserDAO implements IViewUserDAO {
    private Connection connection;

    public SqliteViewUserDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable()
    {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS viewUser ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "groupName VARCHAR NOT NULL,"
                    + "userID INTEGER NOT NULL,"
                    + "FOREIGN KEY (groupName) REFERENCES groupDB(groupName),"
                    + "FOREIGN KEY (userID) REFERENCES user(userID),"
                    + "UNIQUE(groupName, userID)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addViewUser(ViewUser viewUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO viewUser (groupName, userID) VALUES (?, ?)");
            statement.setString(1, viewUser.getGroupName());
            statement.setInt(2, viewUser.getUserID());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add view user failed");
            System.out.println(viewUser.getID());
            e.printStackTrace();
        }
    }

    @Override
    public void updateViewUser(ViewUser viewUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE viewUser SET groupName = ?, userID = ? WHERE id = ?");
            statement.setString(1, viewUser.getGroupName());
            statement.setInt(2, viewUser.getUserID());
            statement.setInt(5, viewUser.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteViewUser(ViewUser viewUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM viewUser WHERE id = ?");
            statement.setInt(1, viewUser.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ViewUser> getAllViewUsers() {
        List<ViewUser> viewUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM viewUser";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String groupName = resultSet.getString("groupName");
                int userID = resultSet.getInt("userID");
                ViewUser viewUser = new ViewUser(groupName, userID);
                viewUser.setID(id);
                viewUsers.add(viewUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewUsers;
    }
}
