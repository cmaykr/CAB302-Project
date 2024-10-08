import com.example.cab302simplestock.model.User;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        String firstName = "John";
        String lastName = "Smith";
        String email = "J.Smith@mail.com";
        String password = "12345";
        user = new User(firstName, lastName, email, password);
    }

    @Test
    void testConstructor() {
        String firstName = "John";
        String lastName = "Smith";
        String email = "J.Smith@mail.com";
        String password = "12345";

        User testUser = new User(firstName, lastName, email, password);

        assert(Objects.equals(testUser.getFirstName(), firstName));
        assert(Objects.equals(testUser.getLastName(), lastName));
        assert(Objects.equals(testUser.getEmail(), email));
        assert(testUser.checkPassword(password));
    }

    @Test
    void testChangeFirstName() {
        String newName = "Erik";
        user.setFirstName(newName);
        assert(Objects.equals(user.getFirstName(), newName));
    }

    @Test
    void testChangeLastName() {
        String newName = "Johnson";
        user.setLastName(newName);
        assert(Objects.equals(user.getLastName(), newName));
    }

    @Test
    void testChangeEmail() {
        String newMail = "Erik@mail.com";
        user.setEmail(newMail);
        assert(Objects.equals(user.getEmail(), newMail));
    }

    @Test
    void testGetPassword() {
        try {
            MessageDigest hashFunc = MessageDigest.getInstance("sha256");

            byte[] encodedhash = hashFunc.digest("12345".getBytes());
            System.out.println(Base64.getEncoder().encodeToString(encodedhash));
            assertEquals(Base64.getEncoder().encodeToString(encodedhash), user.getHashedPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testChangePassword() {
        String newPassword = "54321";
        user.setPassword(newPassword);
        assert(user.checkPassword(newPassword));
    }

    @Test
    void testCheckPassword() {
        user.checkPassword("12345");
    }

    @Test
    void testSetId() {
        user.setID(1);
        assertEquals(user.getID(), 1);
    }
}
