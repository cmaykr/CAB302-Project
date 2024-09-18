package org.example.simplestock.Model;
import java.util.List;
/*
* This class should have all the methods for the products
* The methods can include: \
* add prodcut();
* delete product();
* insert product();
* get product();
* get all products(); */
/* THEN REPALCE THE METHODS BODY WITH SQL QUERIES TO FETCH DATA FROM THE
* DATABASE FOR PRODUCTS */
public interface IProductDAO {
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void deleteProduct(Product product);
    public Product getProduct(String name);
    public List<Product> getAllProducts = List.of();

}
