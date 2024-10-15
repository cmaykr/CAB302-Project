import com.example.cab302simplestock.model.Item;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.cab302simplestock.model.Photo;

public class PhotoTest {
    Photo photo;

    @BeforeEach
    void setUp() {
        photo = new Photo("shoe.png", 2);
    }

    @Test
    void testSetImageName() {
        photo.setImageName("Nike-shoe.png");
        assertEquals("Nike-shoe.png", photo.getImageName());
    }

    @Test
    void testSetItemID() {
        photo.setItemID(30);
        assertEquals(30, photo.getItemID());
    }

    @Test
    void testSetPhotoID() {
        photo.setPhotoID(2);
        assertEquals(2, photo.getPhotoID());
    }

    @Test
    void testSetEmptyPhotoImageNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> photo.setImageName(""));

        assertEquals("Photo image name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetNegativePhotoIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> photo.setPhotoID(-2));

        assertEquals("Photo ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testSetPhotoIDToZeroShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> photo.setPhotoID(0));

        assertEquals("Photo ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }
    @Test
    void testSetNegativePhotoItemIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> photo.setItemID(-1));

        assertEquals("Photo Item ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testSetPhotoItemIDToZeroShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> photo.setItemID(0));

        assertEquals("Photo Item ID cannot be negative, must be a positive value, >0.", exception.getMessage());
    }
}
