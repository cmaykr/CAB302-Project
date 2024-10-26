import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.Managers.UserManager;
import com.example.cab302simplestock.model.Managers.ViewUserManager;
import com.example.cab302simplestock.model.MockDAOs.MockUserDAO;
import com.example.cab302simplestock.model.MockDAOs.MockViewUserDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ViewUserManagerTest {
    ViewUserManager viewUserManager;

    @BeforeEach
    void setUp() {
        UserManager userManager = new UserManager(new MockUserDAO());
        viewUserManager = new ViewUserManager(new MockViewUserDAO(), userManager);
    }

    @Test
    void testAddViewUserShouldReturnValidID() {
        ViewUser newUser = new ViewUser(2, 3);

        int id = viewUserManager.addViewUser(newUser);

        assertEquals(1, id);
    }

    @Test
    void testUpdateViewUserShouldNotThrowException() {
        ViewUser newUser = new ViewUser(2, 3);

        int id = viewUserManager.addViewUser(newUser);
        newUser.setUserID(id);
        newUser.setUserID(5);

        assertDoesNotThrow(() -> viewUserManager.updateViewUser(newUser));
    }

    @Test
    void testDeleteViewUserShouldNotThrowException() {
        ViewUser newUser = new ViewUser(2, 3);

        int id = viewUserManager.addViewUser(newUser);
        newUser.setUserID(id);
        newUser.setUserID(5);

        assertDoesNotThrow(() -> viewUserManager.deleteViewUser(newUser));
    }

    @Test
    void testGetAllViewUsers() {
        ViewUser newUser = new ViewUser(2, 3);
        viewUserManager.addViewUser(newUser);
        List<ViewUser> users = viewUserManager.getAllViewUsers();

        assertEquals(users.size(), 1);
        assertEquals(users.getFirst().getUserID(), newUser.getUserID());
    }

    @Test
    void testFindUserInGroupWithEmptyViewUsersShouldReturnNull() {
        ViewUser foundUser = viewUserManager.findViewUser(2, 2);

        assertNull(foundUser);
    }
}