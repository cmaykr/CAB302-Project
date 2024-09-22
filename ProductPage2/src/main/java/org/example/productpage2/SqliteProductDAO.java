package org.example.productpage2;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import org.example.productpage2.IProductDAO;
import org.example.productpage2.Product;
import org.example.productpage2.SqliteConnection;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteProductDAO implements IProductDAO {
    private Connection connection;

    public SqliteProductDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        //For testing purposes only
        insertSampleData();
    }
    private void insertSampleData(){
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM products";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO products (productName, productType, description, location,quantity, purchaseDate,insured,price) VALUES "
                    + "('Shoes', 'Nike', 'beautiful', 'Garage',2,22/03/2003,yes,'$200'),"
                    + "('Shoes', 'Nike', 'shiny', 'Garage',2,22/03/2004,'yes','$300'),"
                    + "('Shoes', 'gucci', 'ugly', 'Garage',3,20/03/2033,'yes','$300')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    + "quantity INTEGER NOT NULL,"
                    +"purchaseDate timeStamp NOT NULL,"
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
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO products (productName, productType, description, location, quantity, purchaseDate, insured, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS // To get the generated ID
            );
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getProductType());
            statement.setString(3, product.getProductDescription());
            statement.setString(4, product.getProductLocation());
            statement.setInt(5, product.getProductQuantity());
            statement.setDate(6, new java.sql.Date(product.getPurchaseDate().getTime())); // Corrected
            statement.setBoolean(7, product.getInsured()); // For boolean value
            statement.setInt(8, product.getPrice());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE products SET productName = ?, productType = ?, description = ?, location = ?, quantity = ?, purchaseDate = ?, insured = ?, price = ? WHERE id = ?"
            );
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getProductType());
            statement.setString(3, product.getProductDescription());
            statement.setString(4, product.getProductLocation());
            statement.setInt(5, product.getProductQuantity());
            statement.setDate(6, new java.sql.Date(product.getPurchaseDate().getTime())); // Corrected
            statement.setBoolean(7, product.getInsured()); // For boolean value
            statement.setInt(8, product.getPrice());
            statement.setInt(9, product.getId()); // Set the ID for the WHERE clause
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Product product) {
        try {
            // Correct the table name from 'contacts' to 'products' if necessary
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            // Use the statement object to set the id parameter
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(int id) {
        try{
            PreparedStatement statement  = connection .prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String productName = resultSet.getString("productName");
                String productType = resultSet.getString("productType");
                String description = resultSet.getString("productDescription");
                String location = resultSet.getString("productLocation");
                int quantity = resultSet.getInt("productQuantity");
                Date purchaeDate = resultSet.getDate("purchaseDate");
                Boolean insured = (Boolean) resultSet.getObject("Insured");
                int price = resultSet.getInt("productPrice");
                Product product = new Product(id,productName,productType,description,location,quantity,purchaeDate,insured,price);
                product.setId(id);
                return product;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                String productType = resultSet.getString("productType");
                String description = resultSet.getString("productDescription");
                String location = resultSet.getString("productLocation");
                int quantity = resultSet.getInt("productQuantity");
                Date purchaeDate = resultSet.getDate("purchaseDate");
                Boolean insured = (Boolean) resultSet.getObject("Insured");
                int price = resultSet.getInt("productPrice");
                Product product = new Product(id,productName,productType,description,location,quantity,purchaeDate,insured,price);
                product.setId(id);
                products.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}
