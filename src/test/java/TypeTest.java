import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.cab302simplestock.model.Type;

public class TypeTest {
    Type type;

    @BeforeEach
    void setUp() {
        type = new Type("Shoe");
    }

    @Test
    void testGetTypeName() {
        assertEquals("Shoe", type.getName());
    }

    @Test
    void testSetTypeName() {
        type.setName("Door");
        assertEquals("Door", type.getName());
    }
}
