import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.service.EventService;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNull;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testEventService {

    public static EventService es;

    @BeforeClass
    public static void init() {
        es = new EventService();
    }

    @Test
    public void testAddEvent() {

        Event testEvent = new Event();

        assertNull(es.add(testEvent));
    }

    @Test
    public void testGetList() {
        es.list();
    }

    @Test
    public void testUpdateEvent() {
        Event testEvent = new Event();
        assertNull(es.update(testEvent));

    }

}
