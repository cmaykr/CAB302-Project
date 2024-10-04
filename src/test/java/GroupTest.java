import com.example.cab302simplestock.model.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Group;

public class GroupTest {
    Group group;
    User user;

    @BeforeEach
    void setUp()
    {
        user = new User("John", "Smith", "j.Smith@mail.com", "12345");
        group = new Group("HomeGroup", user);
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
        assertEquals(user.getID(), group.getOwnerID());
    }

    @Test
    void testSetOwner()
    {
        User newUser = new User("Erik", "Smith", "j.Smith@mail.com", "54321");
        group.setOwner(newUser);
        assertEquals(newUser.getID(), group.getOwnerID());
    }
}
