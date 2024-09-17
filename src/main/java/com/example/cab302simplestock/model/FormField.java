package com.example.cab302simplestock.model;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FormField {
    private Label label;
    private TextField field;

    public FormField(String labelText) {
        this.label = new Label(labelText);
        this.field = new TextField();
    }

    public Label getLabel() {
        return label;
    }

    public TextField getField() {
        return field;
    }

    public void addTo(GridPane gridPane, int row) {
        gridPane.add(label, 0, row);
        gridPane.add(field, 1, row);
    }
}
