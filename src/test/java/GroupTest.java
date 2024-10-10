import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.cab302simplestock.model.Group;

public class GroupTest {
    Group group;

    @BeforeEach
    void setUp()
    {
        group = new Group("HomeGroup", 1);
    }

    @Test
    void testSetGroupID() {
        group.setGroupID(1);
        assertEquals(1, group.getGroupID());
    }

    @Test
    void testSetGroupName()
    {
        group.setGroupName("CompanyGroup");
        assertEquals("CompanyGroup", group.getGroupName());
    }

    @Test
    void testSetOwnerID()
    {
        group.setOwnerID(2);
        assertEquals(2, group.getOwnerID());
    }

    @Test
    void testSetEmptyGroupNameShouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(Exception.class, () -> group.setGroupName(""));

        assertEquals("Group name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetNegativeOwnerIDShouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(Exception.class, () -> group.setOwnerID(-1));

        assertEquals("New owner ID must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetOwnerIDToZeroShouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(Exception.class, () -> group.setOwnerID(0));

        assertEquals("New owner ID must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetNegativeGroupIDShouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(Exception.class, () -> group.setGroupID(-1));

        assertEquals("Group ID must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetGroupIDToZeroShouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(Exception.class, () -> group.setGroupID(0));

        assertEquals("Group ID must be positive, >0.", exception.getMessage());
    }
}
