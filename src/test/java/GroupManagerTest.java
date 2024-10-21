import com.example.cab302simplestock.model.ActiveGroup;
import com.example.cab302simplestock.model.Group;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GroupManagerTest {

    @BeforeEach
    void setUp() {
        ActiveGroup.getInstance().deselectGroup();
    }

    @AfterAll
    static void destroy() {
        ActiveGroup.getInstance().deselectGroup();
    }

    @Test
    void testSetActiveGroup() {
        Group group = new Group("Home", 1);
        ActiveGroup.getInstance().setActiveGroup(group);

        assertEquals(group.getGroupName(), ActiveGroup.getInstance().getActiveGroup().getGroupName());
    }
}
