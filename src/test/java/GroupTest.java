import com.example.cab302simplestock.model.User;
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
    void testGetGroupName()
    {
        assertEquals("HomeGroup", group.getGroupName());
    }

    @Test
    void testSetGroupName()
    {
        group.setGroupName("CompanyGroup");
        assertEquals("CompanyGroup", group.getGroupName());
    }

    @Test
    void testGetOwner()
    {
        assertEquals(1, group.getOwnerID());
    }

    @Test
    void testSetOwner()
    {
        group.setOwner(2);
        assertEquals(2, group.getOwnerID());
    }

    @Test
    void testSetGroupNameEmptyThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> group.setGroupName(""));

        assertEquals("Group name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetOwnerEmptyThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> group.setOwner(0));

        assertEquals("New owner ID must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetNegativeGroupIDThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> group.setGroupID(-2));

        assertEquals("Group ID must be positive, >0.", exception.getMessage());
    }
}
