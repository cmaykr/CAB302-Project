package org.example.productpage.model;
import org.example.productpage.model.Product;

import java.util.List;

public interface IProductDAO {
    /**
     * Adds a new product to the database.
     * @param product The product to add.
     */
    public void addProduct(Product product);
    /**
     * Updates an existing product in the database.
     * @param product The product to update.
     */
    public void updateProduct(Product product);
    /**
     * Deletes a product from the database.
     * @param product The product to delete.
     */
    public void deleteProduct(Product product);
    /**
     * Retrieves a product from the database.
     * @param id The id of the contact to retrieve.
     * @return The product with the given id, or null if not found.
     */
    public Product getProduct(int id);
    /**
     * Retrieves all products from the database.
     * @return A list of all products in the database.
     */
    public List<Product> getAllProducts();

}
