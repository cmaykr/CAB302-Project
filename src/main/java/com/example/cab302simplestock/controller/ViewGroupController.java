package com.example.cab302simplestock.controller;

import com.example.cab302simplestock.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.List;

public class ViewGroupController {

    @FXML
    private Text groupText;

    private String groupName;

    // This method will set the group name and update the UI
    public void setGroupName(String groupName) {
        this.groupName = groupName;
        groupText.setText("Viewing Group: " + groupName);
    }
    @FXML
    private GridPane itemGrid;

    // Mock data for items, replace with dynamic data fetching logic
    private List<Item> itemList = List.of(
            new Item("Laptop", "2023-03-01", 1000.0, 800.0, "Work laptop", "Electronics", "Work", "Bedroom", true),
            new Item("Table", "2022-11-15", 150.0, 120.0, "Dining table", "Furniture", "Home", "Living room", false),
            new Item("Smartphone", "2023-07-22", 700.0, 650.0, "Personal phone", "Electronics", "Personal", "Bedroom", true)
    );

    @FXML
    public void initialize() {
        // Add column headers
        addGridHeaders();

        // Populate the grid with items
        populateItemGrid();
    }

    // Method to add column headers
    private void addGridHeaders() {
        itemGrid.add(new Text("Item Name"), 0, 0);
        itemGrid.add(new Text("Category"), 1, 0);
        itemGrid.add(new Text("Description"), 2, 0);
        itemGrid.add(new Text("Price"), 3, 0);
        itemGrid.add(new Text("Purchase Date"), 4, 0);
    }

    // Method to populate the grid with items
    private void populateItemGrid() {
        int row = 1; // Start after the header row

        for (Item item : itemList) {
            itemGrid.add(new Label(item.getName()), 0, row);
            itemGrid.add(new Label(item.getTypeName()), 1, row);
            itemGrid.add(new Label(item.getDescription()), 2, row);
            itemGrid.add(new Label(String.valueOf(item.getPurchasePrice())), 3, row);
            itemGrid.add(new Label(item.getPurchaseDate()), 4, row);
            row++;
        }
    }

    // Handler for Add Item button (can be extended)
    @FXML
    private void onAddItemClick() {
        System.out.println("Add Item button clicked");
        // Logic to add a new item (e.g., opening a new item creation form)
    }
}
