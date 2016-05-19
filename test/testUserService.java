import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.service.UserService;
import org.hibernate.StaleStateException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNull;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testUserService {

    public static UserService us;

    @BeforeClass
    public static void init() {
        us = new UserService();
    }

    @Test(expected = StaleStateException.class)
    public void testUpdateUser() {
         us.update(new User());
    }

    @Test
    public void testUserList() {
        Assert.assertNotNull(us.list());
    }

    @Test
    public void testFindUser() {
      Assert.assertNotNull(us.find(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUser() {
        us.delete(null);
    }

    @Test(expected = Exception.class)
    public void testAddUser() {
			User testUser = new User();
			assertNull(us.add(testUser));
    }

    @Test(expected = Exception.class)
    public void testAddUserException() {
        us.add(null);
    }

}
