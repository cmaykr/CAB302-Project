package org.example.simplestock.Model;

import java.sql.*;

// THIS CLASS WILL BE RESPONSIBLE FOR CRUD OPERATIONS WITH THE
// SQLITE DATABASE.
/* THE CLASS HAS THE FOLLOWING
* - CONNECTION OBEJCT TO HANDLE DATABASE OPERATIONS
* - A CONSTRUCTOR TAHT INITILIASES THE CONNECTION OBJECT USING THE SQLITECONNECTION.GETINSTANCE
* - YOU WILL ALSO CALL ON CREATE TABLE() METHOD TO CREAT THE CONTACTS TABLE IF IT DOES NOT EXIST
* - CREATE TABLE() METHOD THAT EXECUTES A SQL QUERY TO CREATE THE CONTACTS TABLE IF IT DOES NOT EXIST */
public class SqliteProductDAO implements IProductDAO{
    private Connection connection;
    public SqliteProductDAO(){
        connection = SqliteConnection.getInstance();
        createTable();
        //Used for testing
        insertSampleData();

    }
    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM contacts";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO products (Name, type, phone, email) VALUES "
                    + "('John', 'Doe', '0423423423', 'johndoe@example.com'),"
                    + "('Jane', 'Doe', '0423423424', 'janedoe@example.com'),"
                    + "('Jay', 'Doe', '0423423425', 'jaydoe@example.com')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createTable(){
        try{
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "productName VARCHAR NOT NULL,"
                    + "productType VARCHAR NOT NULL,"
                    + "description VARCHAR NOT NULL,"
                    + "location VARCHAR NOT NULL,"
                    +"quantity INTEGER NOT NULL,"
                    +"purchaseDate timestamp DateTime,"
                    +"Insured VARCHAR NOT NULL,"
                    +"PRICE INTEGER NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Prodcut> getAllProduct(){
        List<Product> products = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM products";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                String productType = resultSet.getString("productType");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                int quantity = resultSet.getInt("quantity");
                Product product = new Product(id,productName,productType,description,location,quantity)
            }

        }catch(Exception e){
            e.printStackTrace();
        }return products;
    }
    @Override
    public List<Product> getAllContacts(){
        List<Product> contacts = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM contacts";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String productName = resultSet.getString("productName");
                String productType = resultSet.getString("productType");
                String description = resultSet.getString("description");
                String location = resultSet.getString("location");
                int quantity = resultSet.getInt("quantity");
                Product product = new Product(productName,productType,description,location,quantity);
                product.setId(Id);
                return product;
            }
        }catch(Exception e){
            e.printStackTrace();
        }return null;

    }
    @Override
    public void addContact(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (firstName, lastName, phone, email) VALUES (?, ?, ?, ?)");
            statement.setString(1, product.productName());
            statement.setString(2, product.productType());
            statement.setString(3, product.description());
            statement.setString(4, product.location());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addContact(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts (firstName, lastName, phone, email) VALUES (?, ?, ?, ?)");
            statement.setString(1, product.productName());
            statement.setString(2, product.productType());
            statement.setString(3, product.description());
            statement.setString(4, product.location());
            statement.executeUpdate();
            // Set the id of the new contact
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
