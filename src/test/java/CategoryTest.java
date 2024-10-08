import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Category;

public class CategoryTest {
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category("To buy", "Home inventory");
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
}
