import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Item;

public class ItemTest {
    Item item;

    @BeforeEach
    void setUp() {
        int id = 53;
        String name = "Nike Air Shoe";
        int category = 4;
        String description = "Some random guy's shoe";
        double purchasePrice = 172.50;
        String purchaseDate = "11/11/11";
        int quantity = 2; // TODO: Enum?

        item = new Item(id, name, category, description, purchasePrice, purchaseDate, quantity);
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
        assertEquals(2, item.getCategory());
    }

    @Test
    void testSetListName() {
        item.setCategory(1);
        assertEquals(1, item.getCategory());
    }

}
