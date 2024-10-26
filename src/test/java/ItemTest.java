import org.junit.jupiter.api.*;

import com.example.cab302simplestock.model.Item;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    Item item;

    @BeforeEach
    void setUp() {
        String itemName = "Nike Shoe";
        String purchaseDate = "2022-09-16";
        double purchasePrice = 42;
        double value = 30;
        String description = "A white shoe with the Nike logo on it, worn a little";
        int categoryID = 1;
        int typeID = 4;
        String location = "Garage";
        boolean insured = true;

        item = new Item(itemName, purchaseDate, purchasePrice, value, description, categoryID, typeID, location, insured, "testImage.png");
    }

    @Test
    void testSetName() {
        item.setName("Adidas Shoe");
        assertEquals("Adidas Shoe", item.getName());
    }

    @Test
    void testSetPurchaseDate() {
        item.setPurchaseDate("2019-08-10");
        assertEquals("2019-08-10", item.getPurchaseDate());
    }

    @Test
    void testSetPurchasePrice() {
        item.setPurchasePrice(50);
        assertEquals(50, item.getPurchasePrice());
    }

    @Test
    void testSetDescription() {
        item.setDescription("A black adidas shoe, never worn.");
        assertEquals("A black adidas shoe, never worn.", item.getDescription());
    }

    @Test
    void testSetCategoryID() {
        item.setCategoryID(2);
        assertEquals(2, item.getCategoryID());
    }

    @Test
    void testSetTypeID() {
        item.setTypeID(6);
        assertEquals(6, item.getTypeID());
    }

    @Test
    void testSetQuantity() {
        item.setQuantity(24);
        assertEquals(24, item.getQuantity());
    }

    @Test
    void testSetLocation() {
        item.setLocation("Living room");
        assertEquals("Living room", item.getLocation());
    }

    @Test
    void testSetInsured() {
        item.setInsured(false);
        assertFalse(item.getInsured());
    }

    @Test
    void testSetItemID() {
        item.setItemID(2);
        assertEquals(2, item.getItemID());
    }

    @Test
    void testSetEmptyItemNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setName(""));

        assertEquals("Item name cannot be empty.", exception.getMessage());
    }


    @Test
    void testSetNegativeItemPurchasePriceShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setPurchasePrice(-1));

        assertEquals("Item purchase price cannot be negative.", exception.getMessage());
    }

    @Test
    void testSetNegativeItemQuantityShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setQuantity(-1));

        assertEquals("Item quantity cannot be negative, must be a positive value.", exception.getMessage());
    }

    @Test
    void testSetNegativeItemCategoryIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setCategoryID(-1));

        assertEquals("Item category ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testSetItemCategoryIDToZeroShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setCategoryID(0));

        assertEquals("Item category ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testSetNegativeItemTypeIDThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setTypeID(-1));

        assertEquals("Item type ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testSetItemTypeIDToZeroShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setTypeID(0));

        assertEquals("Item type ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testSetNegativeItemIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setItemID(-1));

        assertEquals("Item ID cannot be negative, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetItemIDToZeroShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> item.setItemID(0));

        assertEquals("Item ID cannot be negative, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetImagePath() {
        item.setImagePath("NewImage.png");

        assertEquals(item.getImagePath(), "NewImage.png");
    }


}
