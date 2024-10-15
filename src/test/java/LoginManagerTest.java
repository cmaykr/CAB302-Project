import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.LoginManager;

public class LoginManagerTest {
    LoginManager loginManager;

    @BeforeEach
    void setUp() {
        list = new Category("To buy", "Home inventory");
    }

    @Test
    void testGetListName() {
        assertEquals("To buy", list.getCategoryName());
    }

    @Test
    void testSetListName() {
        list.setCategoryName("Bought items");
        assertEquals("Bought items", list.getCategoryName());
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
