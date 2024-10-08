import com.example.cab302simplestock.model.ViewUser;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ViewUserTest {
    ViewUser viewUser;

    @BeforeEach
    void setUp() {
        viewUser = new ViewUser("HomeGroup", 2);
    }

    @Test
    void testGetID() {
        viewUser.setID(1);
        assertEquals(1, viewUser.getID());
    }
    @Test
    void testGetGroupName() {
        assertEquals("HomeGroup", viewUser.getGroupName());
    }

    @Test
    void testSetGroupName() {
        viewUser.setGroupName("WorkGroup");
        assertEquals("WorkGroup", viewUser.getGroupName());
    }

    @Test
    void testGetUserID() {
        assertEquals(2, viewUser.getUserID());
    }

    @Test
    void testSetUserID() {
        viewUser.setUserID(3);
        assertEquals(3, viewUser.getUserID());
    }

}
