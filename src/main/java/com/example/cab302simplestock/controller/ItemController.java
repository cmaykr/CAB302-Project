package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.model.FormField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ItemController {

    @FXML
    private GridPane gridPane;

    private FormField nameField = new FormField("Name:");
    private FormField categoryField = new FormField("Category:");
    private FormField descriptionField = new FormField("Description:");
    private FormField priceField = new FormField("Price:");
    private FormField dateField = new FormField("Date:");

    @FXML
    private void initialize() {
        nameField.addTo(gridPane, 0);
        categoryField.addTo(gridPane, 1);
        descriptionField.addTo(gridPane, 2);
        priceField.addTo(gridPane, 3);
        dateField.addTo(gridPane, 4);
    }

    // Getters for fields if needed
    public TextField getNameField() {
        return nameField.getField();
    }

    public TextField getCategoryField() {
        return categoryField.getField();
    }

    public TextField getDescriptionField() {
        return descriptionField.getField();
    }

    public TextField getPriceField() {
        return priceField.getField();
    }

    public TextField getDateField() {
        return dateField.getField();
    }

}