import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.cab302simplestock.model.Category;

public class CategoryTest {
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category("To buy", "Home inventory");
    }

    @Test
    void testSetCategoryID() {
        category.setCategoryID(2);
        assertEquals(2, category.getCategoryID());
    }

    @Test
    void testGetCategoryName() {
        assertEquals("To buy", category.getCategoryName());
    }

    @Test
    void testSetCategoryName() {
        category.setCategoryName("Bought items");
        assertEquals("Bought items", category.getCategoryName());
    }

    @Test
    void testGetGroupName() {
        assertEquals("Home inventory", category.getGroupName());
    }

    @Test
    void testSetGroupName() {
        category.setGroupName("Company inventory");
        assertEquals("Company inventory", category.getGroupName());
    }

    @Test
    void testSetCategoryIDThrowsExceptionAtNegativeID() {
        Exception exception = assertThrows(Exception.class, () -> category.setCategoryID(-1));

        assertEquals("Category ID cannot be negative.", exception.getMessage());
    }

    @Test
    void testSetCategoryNameEmptyThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> category.setCategoryName(""));

        assertEquals("Category name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetGroupNameEmptyThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> category.setGroupName(""));

        assertEquals("Category group name cannot be empty.", exception.getMessage());
    }
}
