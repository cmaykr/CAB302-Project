import com.example.cab302simplestock.model.User;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
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
        String secQuestion = "Which university did you study at?";
        String secAnswer = "QUT";
        user = new User(firstName, lastName, email, password, secQuestion, secAnswer);
    }

    @Test
    void testSetFirstName() {
        String newName = "Ella";
        user.setFirstName(newName);
        assertEquals("Ella", user.getFirstName());
    }

    @Test
    void testSetLastName() {
        String newName = "Johnson";
        user.setLastName(newName);
        assertEquals("Johnson", user.getLastName());
    }

    @Test
    void testSetEmail() {
        user.setEmail("Smith@mail.com");
        assertEquals("Smith@mail.com", user.getEmail());
    }

    @Test
    void testSetPassword() {
        user.setPassword("6789");
        try {
            MessageDigest hashFunc = MessageDigest.getInstance("sha256");
            byte[] encodedhash = hashFunc.digest("6789".getBytes());

            assertEquals(Base64.getEncoder().encodeToString(encodedhash), user.getHashedPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSetSecurityQuestion() {
        user.setSecurityQuestion("Where did you grow up?");
        assertEquals("Where did you grow up?", user.getSecurityQuestion());
    }

    @Test
    void testSetSecurityAnswer() {
        user.setSecurityAnswer("UQ");
        assertEquals("UQ", user.getSecurityAnswer());
    }

    @Test
    void testSetUserID() {
        user.setID(2);
        assertEquals(2, user.getID());
    }

    @Test
    void testCheckUserPassword() {
        assertTrue(user.checkPassword("12345"));
    }

    @Test
    void testSetEmptyFirstNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setFirstName(""));

        assertEquals("User first name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetEmptyLastNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setLastName(""));

        assertEquals("User last name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetEmptyEmailShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setEmail(""));

        assertEquals("User email cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetInvalidEmailShouldThrowIllegalFormatException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setEmail("john.com"));

        assertEquals("User email format invalid, use format: name@domain.", exception.getMessage());
    }

    @Test
    void testSetEmptyPasswordShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setPassword(""));

        assertEquals("User password cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetEmptySecurityQuestionShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setSecurityQuestion(""));

        assertEquals("User security question cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetEmptySecurityAnswerShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setSecurityAnswer(""));

        assertEquals("User security answer cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetNegativeUserIDShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> user.setID(-1));

        assertEquals("User ID cannot be negative, must be a positive value, >0.", exception.getMessage());
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
