import com.example.cab302simplestock.model.*;
import com.example.cab302simplestock.model.Managers.CategoryManager;
import com.example.cab302simplestock.model.Managers.GroupManager;
import com.example.cab302simplestock.model.Managers.UserManager;
import com.example.cab302simplestock.model.Managers.ViewUserManager;
import com.example.cab302simplestock.model.MockDAOs.MockCategoryDAO;
import com.example.cab302simplestock.model.MockDAOs.MockGroupDAO;
import com.example.cab302simplestock.model.MockDAOs.MockUserDAO;
import com.example.cab302simplestock.model.MockDAOs.MockViewUserDAO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupManagerTest {
    GroupManager groupManager;

    private final User[] users = {
            new User("Erik", "Smith", "smith@mail.com", "1234", "Which uni did you go to?", "QUT"),
            new User("John", "Smith", "smith@mail.com", "1234", "Which uni did you go to?", "QUT"),
            new User("Smitty", "Smith", "smith@mail.com", "1234", "Which uni did you go to?", "QUT"),
    };

    private final Category[] categories = {
            new Category("To buy", 1),
            new Category("Bought items", 1),
            new Category("To sell", 1),
    };

    @BeforeEach
    void setUp() {
        CategoryManager categoryManager = new CategoryManager(new MockCategoryDAO());
        UserManager userManager = new UserManager(new MockUserDAO());
        ViewUserManager viewUserManager = new ViewUserManager(new MockViewUserDAO(), userManager);
        groupManager = new GroupManager(new MockGroupDAO(), categoryManager, viewUserManager);

        for (User user: users) {
            userManager.addUser(user);
        }
    }

    @Test
    void testAddGroup() {
        Group group = new Group("Home", 2);
        int expectedGroupID = 1;

        int actualGroupID = groupManager.addGroup(group);

        assertEquals(expectedGroupID, actualGroupID);
    }

    @Test
    void testGetAllUsersInGroup() {

        groupManager.addUser(users[0], 1);
        groupManager.addUser(users[1], 1);

        List<User> viewUsers = groupManager.getUsers(1);

        assertEquals(2, viewUsers.size());
        assertEquals(users[0], viewUsers.get(0));
        assertEquals(users[1], viewUsers.get(1));
    }

    @Test
    void testGetAllUsersInGroupWithNegativeGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.getUsers(-1));

        assertEquals("Group ID set to invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testGetAllUsersInGroupWithZeroGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.getUsers(0));

        assertEquals("Group ID set to invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testFindUserInGroupWithSingleUser() {
        groupManager.addUser(users[0], 1);

        User user = groupManager.findUser(users[0].getEmail(), 1);

        assertEquals(user, users[0]);
    }

    @Test
    void testFindUserInGroupWithMultipleUsers() {
        User expectedUser = users[0];
        int expectedUserID = 1;
        expectedUser.setID(expectedUserID);
        int actualUserID = groupManager.addUser(users[0], 1);
        for (int i = 1; i < users.length; i++)
        {
            groupManager.addUser(users[i], 1);
        }

        User actualUser = groupManager.findUser(expectedUser.getEmail(), 1);

        assertEquals(expectedUser, actualUser);
        assertEquals(expectedUserID, actualUserID);
    }

    @Test
    void testFindUserInGroupWithEmptyStringShouldReturnNull() {
        groupManager.addUser(users[0], 1);
        groupManager.addUser(users[0], 1);

        assertNull(groupManager.findUser("", 1));
    }

    @Test
    void testFindUserInGroupWithNoUsersShouldReturnNull() {
        assertNull(groupManager.findUser(users[0].getEmail(), 1));
    }

    @Test
    void testFindUserInGroupWhereUserIsNotInGroupShouldReturnNull() {
        groupManager.addUser(users[1], 1);

        assertNull(groupManager.findUser(users[0].getEmail(), 2));
    }

    @Test
    void testFindUserInGroupWithNegativeGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.findUser(users[0].getEmail(), -1));

        assertEquals("Group ID in findUser invalid value, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testFindUserInGroupWithZeroGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.findUser(users[0].getEmail(), 0));

        assertEquals("Group ID in findUser invalid value, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testAddUserToGroupWithNegativeGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.addUser(users[0], -1));

        assertEquals("Group ID parameter in addUser invalid value, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testAddUserToGroupWithZeroGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.addUser(users[0], 0));

        assertEquals("Group ID parameter in addUser invalid value, must be positive, >0.", exception.getMessage());
    }

    @Test
    void testDeleteUserFromGroup() {
        int userID = groupManager.addUser(users[0], 1);

        assertDoesNotThrow(() -> groupManager.deleteUser(userID, 1));
    }

    @Test
    void testDeleteInvalidUserFromGroupShouldRunCorrectly() {
        assertDoesNotThrow(() -> groupManager.deleteUser(1, 1));
    }

    @Test
    void testDeleteUserFromGroupWithNegativeUserIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.deleteUser(-1, 1));

        assertEquals("User ID parameter in deleteUser has invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testDeleteUserFromGroupWithZeroUserIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.deleteUser(0, 1));

        assertEquals("User ID parameter in deleteUser has invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testDeleteUserFromGroupWithNegativeGroupIDShouldThrowException() {
        int userID = groupManager.addUser(users[0], 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.deleteUser(userID, -1));

        assertEquals("Group ID parameter in deleteUser has invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testDeleteUserFromGroupWithZeroGroupIDShouldThrowException() {
        int userID = groupManager.addUser(users[0], 1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.deleteUser(userID, 0));

        assertEquals("Group ID parameter in deleteUser has invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testGetCategoriesInGroupWithSingleCategory() {
        groupManager.addCategory(categories[0]);

        List<Category> category = groupManager.getCategories(categories[0].getGroupID());

        assertEquals(1, category.size());
        assertEquals(categories[0].getCategoryName(), category.get(0).getCategoryName());
    }

    @Test
    void testGetCategoriesInGroupWithMultipleCategories() {
        for (Category category: categories)
        {
            groupManager.addCategory(category);
        }

        List<Category> allCategories = groupManager.getCategories(categories[0].getGroupID());

        assertEquals(3, allCategories.size());
        assertEquals(categories[0].getCategoryName(), allCategories.get(0).getCategoryName());
    }

    @Test
    void testGetCategoriesInGroupWithNoCategoriesShouldReturnZero() {
        List<Category> categoryList = groupManager.getCategories(1);

        assertEquals(0, categoryList.size());
    }

    @Test
    void testGetCategoriesInGroupWithNegativeGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.getCategories(-1));

        assertEquals("Group ID in getCategories has invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testGetCategoriesInGroupWithZeroGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.getCategories(0));

        assertEquals("Group ID in getCategories has invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testFindCategoryWithSingleCategoryInGroup() {
        groupManager.addCategory(categories[0]);

        Category category = groupManager.findCategory(categories[0].getCategoryName(), categories[0].getGroupID());

        assertEquals(category.getCategoryID(), categories[0].getCategoryID());
        assertEquals(category.getCategoryName(), categories[0].getCategoryName());
        assertEquals(category.getGroupID(), categories[0].getGroupID());
    }

    @Test
    void testFindCategoryWithMultipleCategoriesInGroup() {
        for (Category category: categories)
        {
            groupManager.addCategory(category);
        }

        Category category = groupManager.findCategory(categories[0].getCategoryName(), categories[0].getGroupID());

        assertEquals(category.getCategoryID(), categories[0].getCategoryID());
        assertEquals(category.getCategoryName(), categories[0].getCategoryName());
        assertEquals(category.getGroupID(), categories[0].getGroupID());
    }

    @Test
    void testFindCategoryWithZeroCategoriesInGroupShouldReturnNull() {
        Category category = groupManager.findCategory(categories[0].getCategoryName(), categories[0].getGroupID());

        assertNull(category);
    }

    @Test
    void testFindCategoryWithEmptyCategoryNameShouldReturnNull() {
        for (Category category: categories)
        {
            groupManager.addCategory(category);
        }

        Category category = groupManager.findCategory("", categories[0].getGroupID());

        assertNull(category);
    }

    @Test
    void testFindCategoryWithNegativeGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.findCategory(categories[0].getCategoryName(), -1));

        assertEquals("Group ID parameter in findCategory has an invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testFindCategoryWithZeroGroupIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.findCategory(categories[0].getCategoryName(), 0));

        assertEquals("Group ID parameter in findCategory has an invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testDeleteCategoryShouldNotThrowException() {
        int categoryID = groupManager.addCategory(categories[0]);

        assertDoesNotThrow(() -> groupManager.deleteCategory(categoryID));
    }

    @Test
    void testDeleteCategoryWithNegativeCategoryIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.deleteCategory(-1));

        assertEquals("Category ID in deleteCategory has an invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testDeleteCategoryWithZeroCategoryIDShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> groupManager.deleteCategory(0));

        assertEquals("Category ID in deleteCategory has an invalid value, must be a positive value, >0.", exception.getMessage());
    }

    @Test
    void testUpdateGroupDoesNotThrowException() {
        Group group = new Group("Test", 2);

        int id = groupManager.addGroup(group);

        group.setGroupID(id);
        group.setGroupName("Test2");

        assertDoesNotThrow(() -> groupManager.updateGroup(group));
    }

    @Test
    void testDeleteGroupShouldNotThrowException() {
        Group group = new Group("Test", 2);
        int id = groupManager.addGroup(group);
        group.setGroupID(id);

        assertDoesNotThrow(() -> groupManager.deleteGroup(group));
    }

    @Test
    void testGetAllGroups() {
        Group group = new Group("Test", 2);
        groupManager.addGroup(group);
        List<Group> groupList = groupManager.getAllGroups();

        assertEquals(groupList.size(), 1);
        assertEquals(groupList.getFirst().getGroupName(), "Test");
    }
}