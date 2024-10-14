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

public class SqliteTypeDAO implements ITypeDAO {
    Connection connection;

    public SqliteTypeDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS type ("
                    + "name VARCHAR PRIMARY KEY"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addType(Type type) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO type (name) VALUES (?)");
            statement.setString(1, type.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateType(Type type) {
        // FIXME What to update?
    }

    @Override
    public void deleteType(Type type) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM type WHERE name = ?");
            statement.setString(1, type.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM type";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String typeName = resultSet.getString("name");
                Type type = new Type(typeName);
                types.add(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }
}
