import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.MockDAOs.MockCategoryDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryManagerTest {
    CategoryManager categoryManager;

    @BeforeEach
    void setUp() {
        categoryManager = new CategoryManager(new MockCategoryDAO());
    }

    @Test
    void testFindCategoryInGroup() {
        Category category = new Category("TestCat", 2);
        int id = categoryManager.addCategory(category);

        Category foundCat = categoryManager.findCategoryInGroup(id, 2);

        assertEquals(foundCat.getCategoryName(), category.getCategoryName());
        assertEquals(foundCat.getGroupID(), category.getGroupID());
    }

    @Test
    void testFindCategoryInGroupWithNoExistingCategoryShouldReturnNull() {
        Category foundCat = categoryManager.findCategoryInGroup(1, 1);

        assertNull(foundCat);
    }

    @Test
    void testFindCategoryByName() {
        Category category = new Category("TestCat", 2);
        categoryManager.addCategory(category);

        Category foundCat = categoryManager.findCategoryInGroupByName("TestCat", 2);

        assertEquals(foundCat.getCategoryName(), category.getCategoryName());
        assertEquals(foundCat.getGroupID(), category.getGroupID());
    }

    @Test
    void testGetAllCategories() {
        Category category = new Category("TestCat", 2);
        categoryManager.addCategory(category);

        List<Category> foundCats = categoryManager.getAllCategories();

        assertEquals(foundCats.size(), 1);
        assertEquals(foundCats.getFirst().getGroupID(), category.getGroupID());
    }


}