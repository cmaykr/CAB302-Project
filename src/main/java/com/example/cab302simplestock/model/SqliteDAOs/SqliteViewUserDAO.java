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

/**
 * Sqlite implementation of the ViewUser DAO interface.
 */
public class SqliteViewUserDAO implements IViewUserDAO {
    private Connection connection;

    /**
     * Connects to the Sqlite database and creates the viewUser table, if it doesn't exist.
     */
    public SqliteViewUserDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates a Sqlite database table for viewUSer.
     * The columns are viewUser ID, the group ID it's associated with, the user ID the viewUser is of. The groupID + userID are unique in the table.
     */
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

    /**
     * Adds a viewUser to the Sqlite database. The viewUser ID is set automatically by the database.
     * @param viewUser The viewUser that should be added to the database.
     */
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

    /**
     * Updates a viewUser that already exists in the database. The viewUser is chosen by its ID value.
     * If no viewUser with that ID exists nothing is updated in the database.
     * @param viewUser The viewUser that should be updated.
     */
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

    /**
     * Deletes a viewUser in the Sqlite database, looks for the type with the same ID as the parameter one. No other checks other than the ID is made.
     * @param viewUser The viewUser to be deleted. The ID value should be set to the ID value for the viewUser to be deleted.
     */
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

    /**
     * Gets every viewUser stored in the database, does no checks for the viewUsers.
     * @return A list of all viewUsers in the Sqlite database.
     */
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
