import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Ticket;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testTicket {

    public static Ticket t;

    @BeforeClass
    public static void init() {
        t = new Ticket();
    }

    @Test
    public void testGetSetId() {

        t.setId(5);

        int compare = t.getId();

        assertEquals(compare, 5);
    }

    @Test
    public void testGetSetPrice() {

        t.setPrice(100);

        int compare = t.getPrice();

        assertEquals(compare, 100);

    }

    @Test
    public void testGetSetSeat() {

        t.setSeat("lounge");

        String compare = t.getSeat();

        assertNotNull(compare);

    }

    @Test
    public void testGetSetEventId() {

        t.setEventId(1);

        int compare = t.getEventId();

        assertEquals(compare, 1);

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

        int compare = -1859391140;

        assertEquals(compare, t.hashCode());

    }

}
