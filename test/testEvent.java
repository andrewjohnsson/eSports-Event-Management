import by.bsuir.spp.ils.lab.entity.Event;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.*;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testEvent {

    public static Event e;

    @BeforeClass
    public static void init() {
        e = new Event();
    }

    @Test
    public void testGetSetId() {

        e.setId(5);

        int compare = e.getId();

        assertEquals(compare, 5);
    }

    @Test
    public void testGetSetName() {

        e.setName("qwe");

        String compare = e.getName();

        assertNotNull(compare);

    }

    @Test
    public void testGetSetDate() {

        e.setDate("12:11:2015");

        String compare = e.getDate();

        assertNotNull(compare);

    }

    @Test
    public void testEquals() {

        Object testObject = new Object();
        Object secondObject = new Object();

        assertFalse(e.equals(testObject));

    }

    @Test
    public void testHashcode() {

        Event testEvent = new Event();
        testEvent.setId(5);

        int compare = -1214426807;

        assertEquals(compare, e.hashCode());

    }

    @Test
    public void getHash() {

        Event testEvent = new Event();
        testEvent.setId(5);

        int compare = -1214426807;

        assertEquals(compare, e.hashCode());

    }
    @Test
    public void testHash() {

        Event testEvent = new Event();
        testEvent.setId(5);

        int compare = -1214426807;

        assertEquals(compare, e.hashCode());

    }
    @Test
    public void testCode() {

        Event testEvent = new Event();
        testEvent.setId(5);

        int compare = -1214426807;

        assertEquals(compare, e.hashCode());

    }

}
