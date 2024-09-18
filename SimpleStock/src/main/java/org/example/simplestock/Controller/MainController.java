package org.example.simplestock.Controller;
package org.example.simplestock.Controller.Model.SqliteProductDAO;
import com.sun.source.tree.UsesTree;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.example.simplestock.Model.IProductDAO;
import org.example.simplestock.Model.Product;

import javax.swing.*;

public class MainController {
    @FXML
    private TextField prodcutNameTextField;
    @FXML
    private TextField productTypeTextFeild;
    @FXML
    private TextField productDescription;
    @FXML
    private TextField productLocation;
    @FXML
    private TextField productQuantity;
    @FXML
    private timestamp DateTime;
    @FXML
    private RadioButton productInsured;
    @FXML
    private TextField productPrice;

    private ListView<Product> contactsListView;
    private IProductDAO productDAO;
    private ListCell<Product> renderCell(ListView<Product> contactListView) {
        return new ListCell<>() {
            /**
             * Updates the item in the cell by setting the text to the contact's full name.
             * @param product The contact to update the cell with.
             * @param empty Whether the cell is empty.
             */
            @Overrides
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
                if (empty || product == null || product.getproductName() == null) {
                    setText(null);
                } else {
                    setText(product.productName());
                }
            }
        };
    }
    @FXML
    public void initialize() {
        contactsListView.setCellFactory(this::renderCell);
        contactsListView.getItems().addAll(productDAO.getAllProducts());
    }
    public MainController() {
        contactDAO = new SqliteContactDAO();
    }
    /**
     * Synchronizes the products list view with the products in the database.
     */
    private void syncContacts() {
        contactsListView.getItems().clear();
        List<Product> contacts = productDAO.getAllProducts();
        boolean hasContact = !contacts.isEmpty();
        if (hasContact) {
            contactsListView.getItems().addAll(products);
        }
        // Show / hide based on whether there are contacts
        productContainer.setVisible(hasContact);
    }


    @FXML
    public void initialize() {
        productsListView.setCellFactory(this::renderCell);
        syncProducts();
        productsListView.getSelectionModel().selectFirst();
        Product firstContact = contactsListView.getSelectionModel().getSelectedItem();
        if (firstContact != null) {
            selectProduct(firstContact);
        }
    }
    @FXML
    private void onEditConfirm(){
        //Get the selected product from the list view
        JColorChooser ProductsListView;
        Product selectedProduct = ProductsListView.getSelectionModel().getSelectedItem();
        if(selectProduct !=null){
            selectedProduct.setProductName(prodcutNameTextField.getText());
            selectedProduct.setProductType(productTypeTextFeild.getText());
            selectedProduct.setProductDescription(productDescription.getText());
            selectedProduct.setProductLocation(productLocation.getText());
            selectedProduct.setProductQuantity(productQuantity.getText());
            selectedProduct.setProductInsured(productInsured.getText());
            selectedProduct.setProductPrice(productPrice.getText());
            productDAO.updateProduct(selectedProduct);
            syncContacts();
        }
    }
    @FXML
    private void onDelete(){
        Product selectedProduct = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productDAO.deleteProduct(selectedProduct);
            syncContacts();
        }
    }
    @FXML
    private void onAdd(){
        final String DEFAULT_PRODUCT_NAME = "New";
        final String DEFAULT_PRODUCT_TYPE = "Product";
        final String DEFAULT_DESCRIPTION = "";
        final String DEFAULT_QUANTITY = "";
        final String DEFAULT_LOCATION = "";
        final int DEFAULT_PRICE = "";
        final int DEFAULT_INSURED = "";
        Product newContact = new Product(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_TYPE, DEFAULT_DESCRIPTION, DEFAULT_QUANTITY);
        // Add the new contact to the database
        productDAO.addProduct(newContact);
        syncContacts();
        // Select the new contact in the list view
        // and focus the first name text field
        selectProduct(newProduct);
        productNameTextField.requestFocus();
    }

}
@FXML
private void onCancel() {
    // Find the selected contact
    Product selectedContact = productsListView.getSelectionModel().getSelectedItem();
    if (selectedContact != null) {
        // Since the contact hasn't been modified,
        // we can just re-select it to refresh the text fields
        selectContact(selectedContact);
    }
}
