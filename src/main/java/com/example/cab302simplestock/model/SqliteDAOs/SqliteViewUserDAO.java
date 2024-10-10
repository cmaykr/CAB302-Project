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
                    + "viewUserID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "groupID VARCHAR NOT NULL,"
                    + "userID INTEGER NOT NULL,"
                    + "FOREIGN KEY (groupID) REFERENCES groupDB(groupID),"
                    + "FOREIGN KEY (userID) REFERENCES user(userID),"
                    + "UNIQUE(groupID, userID)"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addViewUser(ViewUser viewUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO viewUser (groupID, userID) VALUES (?, ?)");
            statement.setInt(1, viewUser.getGroupID());
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
            PreparedStatement statement = connection.prepareStatement("UPDATE viewUser SET groupID = ?, userID = ? WHERE viewUserID = ?");
            statement.setInt(1, viewUser.getGroupID());
            statement.setInt(2, viewUser.getUserID());
            statement.setInt(3, viewUser.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteViewUser(ViewUser viewUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM viewUser WHERE viewUserID = ?");
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
                int id = resultSet.getInt("viewUserID");
                int groupID = resultSet.getInt("groupID");
                int userID = resultSet.getInt("userID");
                ViewUser viewUser = new ViewUser(groupID, userID);
                viewUser.setID(id);
                viewUsers.add(viewUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewUsers;
    }
}
