import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testTeam {

    public static Team t;

    @BeforeClass
    public static void init() {
        t = new Team();
    }

    @Test
    public void testGetSetId() {

        t.setId(5);

        int compare = t.getId();

        assertEquals(compare, 5);
    }

    @Test
    public void testGetSetName() {

        t.setName("qwe");

        String compare = t.getName();

        assertNotNull(compare);

    }

    @Test
    public void testGetSetUserId() {

        t.setUserId(1);

        int compare = t.getUserId();

        assertEquals(compare, 1);

    }

    @Test
    public void testEquals() {

        Object testObject = new Object();
        Object secondObject = new Object();

        assertFalse(t.equals(testObject));

    }

    @Test
    public void testHashcode() {

        Event testEvent = new Event();
        testEvent.setId(5);

        int compare = 3488678;

        assertEquals(compare, t.hashCode());

    }

}
