import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.cab302simplestock.model.Type;

public class TypeTest {
    Type type;

    @BeforeEach
    void setUp() {
        type = new Type("Shoe");
    }

    @Test
    void testSetTypeName() {
        type.setName("Door");
        assertEquals("Door", type.getName());
    }

    @Test
    void setSetTypeID() {
        type.setTypeID(2);
        assertEquals(2, type.getTypeID());
    }

    @Test
    void testSetEmptyTypeNameShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> type.setName(""));

        assertEquals("Type name cannot be empty.", exception.getMessage());
    }

    @Test
    void testSetNegativeTypeIDThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> type.setTypeID(-1));

        assertEquals("Type ID cannot be negative, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testSetTypeIDToZeroShouldThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> type.setTypeID(0));

        assertEquals("Type ID cannot be negative, must be positive, >0.", exception.getMessage());
    }
}
