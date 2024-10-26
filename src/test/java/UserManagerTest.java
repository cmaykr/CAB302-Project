import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.Managers.UserManager;
import com.example.cab302simplestock.model.MockDAOs.MockUserDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {
    UserManager userManager;

    @BeforeEach
    void setUp() {
        userManager = new UserManager(new MockUserDAO());
    }

    @Test
    void testAddUserShouldReturnValidID() {
        User user = new User("John", "John", "1234@mail.com", "1234", "TEst", "TEst");

        int id = userManager.addUser(user);

        assertEquals(1, id);
    }

    @Test
    void testUpdateUserShouldNotThrowException() {
        User user = new User("John", "John", "1234@mail.com", "1234", "TEst", "TEst");

        int id = userManager.addUser(user);
        user.setID(id);
        user.setEmail("Test@mail.com");

        assertDoesNotThrow(() -> userManager.updateUser(user));
    }

    @Test
    void testDeleteUserShouldNotThrowException() {
        User user = new User("John", "John", "1234@mail.com", "1234", "TEst", "TEst");

        int id = userManager.addUser(user);
        user.setID(id);

        assertDoesNotThrow(() -> userManager.deleteUser(user));
    }

    @Test
    void testGetAllUsers() {
        User user = new User("John", "John", "1234@mail.com", "1234", "TEst", "TEst");
        userManager.addUser(user);

        List<User> users = userManager.getAllUsers();

        assertEquals(users.size(), 1);
        assertEquals(users.getFirst().getFirstName(), "John");
    }
}