package org.example.productpage2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {
    public MainController(){
        productDAO = new SqliteProductDAO();
    }
    @FXML
    private ListView<Product> productsListView;
    private IProductDAO productDAO;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField productTypeTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField purchaseDateTextField;
    @FXML
    private CheckBox insuredTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private VBox productContainer;
    @FXML
    private Button addItemsButton;
    @FXML
    private Button backButton;
    @FXML
    protected void addItemsButton() throws IOException {
        Stage stage = (Stage)addItemsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    protected void backButtonClick() throws IOException{
        Stage stage = (Stage)backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),HelloApplication.WIDTH,HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
    /**
     * Programmatically selects a contact in the list view and
     * updates the text fields with the contact's information.
     * @param product The contact to select.
     */
    private void selectProduct(Product product) {
        productsListView.getSelectionModel().select(product);
        productNameTextField.setText(product.getProductName());
        productTypeTextField.setText(product.getProductType());
        descriptionTextField.setText(product.getProductDescription());
        locationTextField.setText(product.getProductLocation());
        quantityTextField.setText(String.valueOf(product.getProductQuantity()));
        purchaseDateTextField.setText(product.getPurchaseDate().toString());
        insuredTextField.setSelected(product.getInsured());
        priceTextField.setText(product.getPurchaseDate().toString());
    }
    private ListCell<Product> renderCell(ListView<Product> productListView) {
        return new ListCell<>() {
            /**
             * Handles the event when a contact is selected in the list view.
             * @param mouseEvent The event to handle.
             */
            private void onContactSelected(MouseEvent mouseEvent) {
                ListCell<Product> clickedCell = (ListCell<Product>) mouseEvent.getSource();
                // Get the selected contact from the list view
                Product selectedContact = clickedCell.getItem();
                if (selectedContact != null) selectProduct(selectedContact);
            }

            /**
             * Updates the item in the cell by setting the text to the contact's full name.
             * @param product The contact to update the cell with.
             * @param empty Whether the cell is empty.
             */
//            @Override
//            protected void updateProduct(Product product, boolean empty) {
//                super.updateItem(product, empty);
//                // If the cell is empty, set the text to null, otherwise set it to the contact's full name
//                if (empty || product == null || product.getProductName() == null) {
//                    setText(null);
//                    super.setOnMouseClicked(this::onContactSelected);
//                } else {
//                    setText(product.getProductName());
//                }
//            }
        };
    }
    private void syncProducts() {
        productsListView.getItems().clear();
        List<Product> contacts = productDAO.getAllProducts();
        boolean hasContact = !contacts.isEmpty();
        if (hasContact) {
            productsListView.getItems().addAll(contacts);
        }
        // Show / hide based on whether there are contacts
        productContainer.setVisible(hasContact);
    }

    @FXML
    public void initialize() {
        productsListView.setCellFactory(this::renderCell);
        syncProducts();
        // Select the first contact and display its information
        productsListView.getSelectionModel().selectFirst();
        Product firstContact = productsListView.getSelectionModel().getSelectedItem();
        if (firstContact != null) {
            selectProduct(firstContact);
        }
    }
    private void syncContacts() {
        productsListView.getItems().clear();
        List<Product> contacts = productDAO.getAllProducts();
        boolean hasContact = !contacts.isEmpty();
        if (hasContact) {
            productsListView.getItems().addAll(contacts);
        }
        // Show / hide based on whether there are contacts
        productContainer.setVisible(hasContact);
    }
//    @FXML
//    private void onEditConfirm() {
//        // Get the selected contact from the list view
//        Product selectedProduct = productsListView.getSelectionModel().getSelectedItem();
//        if (selectedProduct != null) {
//            selectedProduct.setProductName(productNameTextField.getText());
//            selectedProduct.setProductType(productTypeTextField.getText());
//            selectedProduct.setProductDescription(descriptionTextField.getText());
//            selectedProduct.setProductLocation(locationTextField.getText());
//            selectedProduct.setProductQuantity(Integer.parseInt(quantityTextField.getText()));
//            selectedProduct.setProductPurchaseDate(purchaseDateTextField.get());
//            selectedProduct.setProductInsured(Boolean.valueOf(insuredTextField.getText()));
//            selectedProduct.setProductPrice(Integer.parseInt(priceTextField.getText()));
//
//            productDAO.updateProduct(selectedProduct);
//            syncContacts();
//        }
//    }
    @FXML
    private void onDelete() {
        // Get the selected contact from the list view
        Product selectedContact = productsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            productDAO.deleteProduct(selectedContact);
            syncContacts();
        }
    }
    @FXML
    private void onCancel() {
        // Find the selected contact
        Product selectedContact = productsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            // Since the contact hasn't been modified,
            // we can just re-select it to refresh the text fields
            selectProduct(selectedContact);
        }
    }


}
