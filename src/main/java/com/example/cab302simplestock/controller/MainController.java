package org.example.productpage.controller;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXML;
import org.example.productpage.model.IProductDAO;
import org.example.productpage.model.Product;
import org.example.productpage.model.SqliteProductDAO;
import javafx.scene.input.MouseEvent;

import java.util.Date;
import java.util.List;

public class MainController {
    @FXML
    private ListView<Product> productsListView;
    private IProductDAO productDAO;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField productTypeTextField;
    @FXML
    private TextField productDescriptionTextField;
    @FXML
    private TextField productLocationTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField insuredField;
    @FXML
    private TextField priceTextField;
    @FXML
    private DatePicker dateField;

    public MainController() {
        productDAO = new SqliteProductDAO();
    }

    @FXML
    private void onEditConfirm() {
        Product selectedProduct = productsListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct.setProductName(productNameTextField.getText());
            selectedProduct.setProductType(productTypeTextField.getText());
            selectedProduct.setProductDescription(productDescriptionTextField.getText());
            selectedProduct.setProductLocation(productLocationTextField.getText());

            try {
                selectedProduct.setQuantity(Integer.parseInt(quantityTextField.getText()));
                selectedProduct.setPrice(Integer.parseInt(priceTextField.getText()));
            } catch (NumberFormatException e) {
                // Handle invalid input for quantity or price
                System.out.println("Invalid input for quantity or price");
                return;
            }

            selectedProduct.setInsured(insuredField.getText());
            productDAO.updateProduct(selectedProduct);
            syncProducts();
        }
    }

    private void selectedProduct(Product product) {
        productsListView.getSelectionModel().select(product);
        productNameTextField.setText(product.getProductName());
        productTypeTextField.setText(product.getProductType());
        productDescriptionTextField.setText(product.getProductDescription());
        productLocationTextField.setText(product.getProductLocation());
        quantityTextField.setText(String.valueOf(product.getQuantity()));
        insuredField.setText(product.getInsured());
        priceTextField.setText(String.valueOf(product.getPrice()));
    }

    private ListCell<Product> renderCell(ListView<Product> productListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if (empty || product == null || product.getProductName() == null) {
                    setText(null);
                    setOnMouseClicked(null);
                } else {
                    setText(product.getProductName());
                    setOnMouseClicked(this::onProductSelected);
                }
            }

            private void onProductSelected(MouseEvent mouseEvent) {
                Product selectedProduct = getItem();
                if (selectedProduct != null) selectedProduct(selectedProduct);
            }
        };
    }

    @FXML
    private void syncProducts() {
        productsListView.getItems().clear();
        List<Product> products = productDAO.getAllProducts();
        if (!products.isEmpty()) {
            productsListView.getItems().addAll(products);
        }
    }

    @FXML
    public void initialize() {
        productsListView.setCellFactory(this::renderCell);
        syncProducts();
        productsListView.getSelectionModel().selectFirst();
        Product firstProduct = productsListView.getSelectionModel().getSelectedItem();
        if (firstProduct != null) {
            selectedProduct(firstProduct);
        }
    }

    @FXML
    private void onDelete() {
        Product selectedProduct = productsListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productDAO.deleteProduct(selectedProduct);
            syncProducts();
        }
    }

    @FXML
    private void onAdd() {
        final String DEFAULT_PRODUCT_NAME = "NEW";
        final String DEFAULT_PRODUCT_TYPE = "SHOES";
        final String DEFAULT_PRODUCT_DESCRIPTION = "BEST SHOES";
        final String DEFAULT_PRODUCT_LOCATION = "";
        final Integer DEFAULT_PRODUCT_QUANTITY = 0;
        final String DEFAULT_PRODUCT_INSURED = "";
        final Integer DEFAULT_PRODUCT_PRICE = 0;
        final Date DEFAULT_PRODUCT_DATE = new Date();  // Using current date as default

        Product newProduct = new Product(
                DEFAULT_PRODUCT_NAME,
                DEFAULT_PRODUCT_TYPE,
                DEFAULT_PRODUCT_DESCRIPTION,
                DEFAULT_PRODUCT_LOCATION,
                DEFAULT_PRODUCT_QUANTITY,
                DEFAULT_PRODUCT_DATE,
                DEFAULT_PRODUCT_INSURED,
                DEFAULT_PRODUCT_PRICE
        );

        productDAO.addProduct(newProduct);
        syncProducts();
        selectedProduct(newProduct);
        productNameTextField.requestFocus();
    }

    @FXML
    private void onCancel() {
        Product selectedProduct = productsListView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            selectedProduct(selectedProduct);
        }
    }
}
