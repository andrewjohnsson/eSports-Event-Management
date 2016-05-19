import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testUser {

    public static User u;

    @BeforeClass
    public static void init() {
        u = new User();
    }

    @Test
    public void testGetSetId() {

        u.setId(5);

        int compare = u.getId();

        assertEquals(compare, 5);
    }

    @Test
    public void testGetSetPassword() {

        u.setPassword("qwe");

        String compare = u.getPassword();

        assertNotNull(compare);

    }

    @Test
    public void testGetSetEmail() {

        u.setEmail("qwe");

        String compare = u.getEmail();

        assertNotNull(compare);

    }

    @Test
    public void testGetSetAge() {

        u.setAge(5);

        int compare = u.getAge();

        assertEquals(compare, 5);
    }

    @Test
    public void testGetSetEventId() {

        u.setEventId(1);

        int compare = u.getEventId();

        assertEquals(compare, 1);

    }

    @Test
    public void testGetSetTeamId() {

        u.setTeamId(1);

        int compare = u.getTeamId();

        assertEquals(compare, 1);

    }

    @Test
    public void testIsAdmin() {

        u.setIsAdmin(true);

        boolean compare = u.getIsAdmin();

        assertTrue(compare);

    }

    @Test
    public void testIsBusy() {

        u.setIsbusy(true);

        boolean compare = u.getIsAdmin();

        assertFalse(compare);
    }

    @Test
    public void testIsPlayer() {

        u.setIsPlayer(true);

        boolean compare = u.getIsPlayer();

        assertTrue(compare);
    }

    @Test
    public void testIsManager() {

        u.setIsManager(true);

        boolean compare = u.getIsManager();

        assertTrue(compare);
    }

    @Test
    public void testIsSupervisor() {

        u.setIsSupervisor(true);

        boolean compare = u.getIsSupervisor();

        assertTrue(compare);
    }

    @Test
    public void testIsViewer() {

        u.setIsViewer(true);

        boolean compare = u.getIsViewer();

        assertTrue(compare);
    }

    @Test
    public void testEquals() {

        Object testObject = new Object();
        Object secondObject = new Object();

        assertFalse(u.equals(testObject));

    }

    @Test
    public void testNotEquals() {

        User testObject = new User();
        User secondObject = new User();
        secondObject.setId(1);

        assertFalse(u.equals(testObject));

    }

    @Test
    public void testHashcode() {

        Event testEvent = new Event();
        testEvent.setId(5);

        int compare = 142693283;

        assertEquals(compare, u.hashCode());

    }

}
