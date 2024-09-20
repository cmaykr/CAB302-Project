import com.example.cab302simplestock.model.Item;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Photo;

public class PhotoTest {
    Photo photo;
    Item item;

    @BeforeEach
    void setUp() {
        item = new Item("Nike Shoe", "2022-09-16", 50, 30, "Test descript", "Home inventory", "shoe");
        photo = new Photo("shoe.png", item);
    }

    @Test
    void testGetImageName() {
        assertEquals("shoe.png", photo.getImageName());
    }

    @Test
    void testSetImageName() {
        photo.setImageName("Nike-shoe.png");
        assertEquals("Nike-shoe.png", photo.getImageName());
    }

    @Test
    void testGetItemID() {
        assertEquals(item.getID(), photo.getItemID());
    }

    @Test
    void testSetItemID() {
        Item newUser = new Item("Adidas Shoe", "2022-09-16", 50, 30, "Test descript", "Home inventory", "shoe");
        photo.setItemID(newUser);
        assertEquals(newUser.getID(), photo.getItemID());
    }
}
