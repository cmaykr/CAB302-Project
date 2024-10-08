import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Item;

public class ItemTest {
    Item item;

    @BeforeEach
    void setUp() {
        String itemName = "Nike Shoe";
        String purchaseDate = "2022-09-16";
        double purchasePrice = 42;
        double value = 30;
        String description = "A white shoe with the Nike logo on it, worn a little";
        String listName = "Owned items";
        String typeName = "Shoe"; // TODO: Enum?

        item = new Item(itemName, purchaseDate, purchasePrice, value, description, listName, typeName);
    }

    @Test
    void testGetName() {
        assertEquals("Nike Shoe", item.getName());
    }

    @Test
    void testSetName() {
        item.setName("Adidas Shoe");
        assertEquals("Adidas Shoe", item.getName());
    }

    @Test
    void testGetPurchaseDate() {
        assertEquals("2022-09-16", item.getPurchaseDate());
    }

    @Test
    void testSetPurchaseDate() {
        item.setPurchaseDate("2019-08-10");
        assertEquals("2019-08-10", item.getPurchaseDate());
    }

    @Test
    void testGetPurchasePrice() {
        assertEquals(42, item.getPurchasePrice());
    }

    @Test
    void testSetPurchasePrice() {
        item.setPurchasePrice(50);
        assertEquals(50, item.getPurchasePrice());
    }

    @Test
    void testGetDescription() {
        assertEquals("A white shoe with the Nike logo on it, worn a little", item.getDescription());
    }

    @Test
    void testSetDescription() {
        item.setDescription("A black adidas shoe, never worn.");
        assertEquals("A black adidas shoe, never worn.", item.getDescription());
    }

    @Test
    void testGetListName() {
        assertEquals("Owned items", item.getCategoryName());
    }

    @Test
    void testSetListName() {
        item.setCategoryName("To buy");
        assertEquals("To buy", item.getCategoryName());
    }

    @Test
    void testGetTypeName() {
        assertEquals("Shoe", item.getTypeName());
    }

    @Test
    void testSetTypeName() {
        item.setTypeName("Boot");
        assertEquals("Boot", item.getTypeName());
    }

    @Test
    void testGetValue() {
        assertEquals(30, item.getValue());
    }

    @Test
    void testSetValue() {
        item.setValue(24);
        assertEquals(24, item.getValue());
    }
}
