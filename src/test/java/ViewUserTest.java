import com.example.cab302simplestock.model.ViewUser;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ViewUserTest {
    ViewUser viewUser;

    @BeforeEach
    void setUp() {
        viewUser = new ViewUser("HomeGroup", 2);
    }

    @Test
    void testSetGroupName() {
        viewUser.setGroupName("WorkGroup");
        assertEquals("WorkGroup", viewUser.getGroupName());
    }

    @Test
    void testSetUserID() {
        viewUser.setUserID(3);
        assertEquals(3, viewUser.getUserID());
    }

    @Test
    void testSetViewUserID() {
        viewUser.setID(2);
        assertEquals(2, viewUser.getID());
    }

    @Test
    void testSetEmptyGroupNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> viewUser.setGroupName(""));

        assertEquals("ViewUser group name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetNegativeUserIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> viewUser.setUserID(-1));

        assertEquals("ViewUser user ID cannot be negative, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetViewUserIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> viewUser.setID(-1));

        assertEquals("ViewUser ID cannot be negative, must be positive, >0.", exception.getMessage());
    }

}
