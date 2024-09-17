import com.example.cab302simplestock.model.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Photo;

public class PhotoTest {
    Photo photo;
    User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Smith", "Smith@mail.com", "12345");
        photo = new Photo("shoe.png", user);
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
        assertEquals(user.getID(), photo.getItemID());
    }

    @Test
    void testSetItemID() {
        User newUser = new User("Erik", "John", "john@mail.com", "54321");
        photo.setItemID(newUser);
        assertEquals(newUser.getID(), photo.getItemID());
    }
}
