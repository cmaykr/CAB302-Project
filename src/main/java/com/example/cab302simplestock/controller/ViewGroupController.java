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
            new Item(12, "Laptop", 32, "Kenovo Dinkpad", 1100.95, "11/11/11", 1),
            new Item(86, "Table", 21, "Made of cherry blossom tears, and generic wood", 10.00, "13/13/13", 5),
            new Item(14, "Smartphone", 32, "Bapple CryPhone", 999.99, "12/12/12", 3)
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
            itemGrid.add(new Label(String.valueOf(item.getId())), 1, row);
            itemGrid.add(new Label(item.getDescription()), 2, row);
            itemGrid.add(new Label("$" + String.valueOf(item.getPurchasePrice())), 3, row);
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
