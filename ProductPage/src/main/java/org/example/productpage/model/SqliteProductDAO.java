package org.example.productpage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SqliteProductDAO implements IProductDAO {
    private Connection connection;

    public SqliteProductDAO() {
        connection = SqliteConnection.getInstance();
    }
    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS products ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "productName VARCHAR NOT NULL,"
                    + "productType VARCHAR NOT NULL,"
                    + "description VARCHAR NOT NULL,"
                    + "location VARCHAR NOT NULL,"
                    + "quantity INTEGER, NOT NULL,"
                    + "insured VARCHAR NOT NULL,"
                    +"price INTEGER NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }


    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
