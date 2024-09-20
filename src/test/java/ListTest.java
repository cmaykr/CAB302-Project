import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Category;

public class ListTest {
    Category list;

    @BeforeEach
    void setUp() {
        list = new Category("To buy", "Home inventory");
    }

    @Test
    void testGetListName() {
        assertEquals("To buy", list.getListName());
    }

    @Test
    void testSetListName() {
        list.setListName("Bought items");
        assertEquals("Bought items", list.getListName());
    }

    @Test
    void testGetGroupName() {
        assertEquals("Home inventory", list.getGroupName());
    }

    @Test
    void testSetGroupName() {
        list.setGroupName("Company inventory");
        assertEquals("Company inventory", list.getGroupName());
    }
}
