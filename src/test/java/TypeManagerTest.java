import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.MockDAOs.MockCategoryDAO;
import com.example.cab302simplestock.model.MockDAOs.MockTypeDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TypeManagerTest {
    TypeManager typeManager;

    @BeforeEach
    void setUp() {
        typeManager = new TypeManager(new MockTypeDAO());
    }


    @Test
    void testAddTypeShouldReturnValidID() {
        Type type = new Type("Test");

        int id = typeManager.addType(type);

        assertEquals(1, id);
    }

    @Test
    void testUpdateTypeShouldNotThrowException() {
        Type type = new Type("Test");

        int id = typeManager.addType(type);
        type.setTypeID(id);
        type.setName("Test2");

        assertDoesNotThrow(() -> typeManager.updateType(type));
    }

    @Test
    void testDeleteTypeShouldNotThrowException() {
        Type type = new Type("Test");

        int id = typeManager.addType(type);
        type.setTypeID(id);

        assertDoesNotThrow(() -> typeManager.deleteType(type));
    }

    @Test
    void testGetAllTypes() {
        Type type = new Type("Test");

        typeManager.addType(type);
        List<Type> types = typeManager.getAllTypes();

        assertEquals(types.size(),1);
        assertEquals(types.getFirst().getName(), "Test");
    }

    @Test
    void testGetTypeByName() {
        Type type = new Type("Test");

        typeManager.addType(type);

        Type foundType = typeManager.getType("Test");
        assertEquals(foundType.getName(), type.getName());

    }

    @Test
    void testGetTypeWithNoExistingTypeShouldReturnType() {
        Type foundType = typeManager.getType("Test");
        assertEquals(foundType.getName(), "Test");
    }

    @Test
    void testGetTypeByID() {
        Type type = new Type("Test");

        int id = typeManager.addType(type);

        Type foundType = typeManager.getTypeByID(id);
        assertEquals(foundType.getName(), type.getName());
    }
}