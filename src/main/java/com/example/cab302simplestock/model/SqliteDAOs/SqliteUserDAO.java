package com.example.cab302simplestock.model.SqliteDAOs;

import com.example.cab302simplestock.model.InterfaceDAOs.IUserDAO;
import com.example.cab302simplestock.model.SqliteConnection;
import com.example.cab302simplestock.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private Connection connection;

    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS user ("
                    + "userID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "hashedPassword VARCHAR NOT NULL,"
                    + "securityQuestion VARCHAR NOT NULL,"
                    + "securityAnswer VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (firstName, lastName, email, hashedPassword, securityQuestion, securityAnswer) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getHashedPassword());
            statement.setString(5, user.getSecurityQuestion());
            statement.setString(6, user.getSecurityAnswer());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET firstName = ?, lastName = ?, email = ?, hashedPassword = ?, securityQuestion = ?, securityAnswer = ?" +
                    " WHERE userID = ?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getHashedPassword());
            statement.setString(5, user.getSecurityQuestion());
            statement.setString(6, user.getSecurityAnswer());
            statement.setInt(7, user.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE userID = ?");
            statement.setInt(1, user.getID());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("hashedPassword");
                String secQuestion = resultSet.getString("securityQuestion");
                String secAnswer = resultSet.getString("securityAnswer");
                User user = new User(firstName, lastName, email, password, secQuestion, secAnswer);
                user.setID(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String hashedPassword = resultSet.getString("hashedPassword");
                String securityQuestion = resultSet.getString("securityQuestion");
                String securityAnswer = resultSet.getString("securityAnswer");
                user = new User(firstName, lastName, email, hashedPassword, securityQuestion, securityAnswer);
                user.setHashedPassword(hashedPassword);
                user.setID(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
