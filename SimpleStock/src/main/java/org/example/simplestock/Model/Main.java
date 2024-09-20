package org.example.simplestock.Model;
import java.sql.Connection;
import java.sql.SqliteConnection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getInstance();
    }
}