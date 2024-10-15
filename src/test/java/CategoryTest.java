import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.cab302simplestock.model.Category;

public class CategoryTest {
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category("To buy", 2);
    }

    @Test
    void testSetCategoryID() {
        category.setCategoryID(2);
        assertEquals(2, category.getCategoryID());
    }

    @Test
    void testSetCategoryName() {
        category.setCategoryName("Bought items");
        assertEquals("Bought items", category.getCategoryName());
    }

    @Test
    void testSetGroupID() {
        category.setGroupID(3);
        assertEquals(3, category.getGroupID());
    }

    @Test
    void testSetNegativeIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> category.setCategoryID(-1));

        assertEquals("Category ID cannot be negative, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetEmptyCategoryNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> category.setCategoryName(""));

        assertEquals("Category name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetNegativeGroupIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> category.setGroupID(-1));

        assertEquals("Category group ID cannot be negative, must be positive, >0.", exception.getMessage());
    }

}
