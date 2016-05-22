import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.service.TicketService;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNull;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testTicketService {

    public static TicketService ts;

    @BeforeClass
    public static void init() {
        ts = new TicketService();
    }

    @Test(expected = Exception.class)
    public void testAddTicket() {

        Ticket testTicket = new Ticket();

        ts.add(testTicket);

    }

    @Test
    public void testList() {
        ts.list();
    }

    @Test
    public void testFindEventTickets() {
        Event testEvent = new Event();
        ts.findEventTickets(testEvent);
		}

    @Test
    public void testFindTicketsById() {
        Event testEvent = new Event();
        testEvent.setId(1);
        ts.findEventTickets(testEvent);
    }

    @Test
    public void testFindTicketsByName() {
        Event testEvent = new Event();
        testEvent.setName("Starladder");
        ts.findEventTickets(testEvent);
    }

    @Test
    public void testFindTicketsByDate() {
        Event testEvent = new Event();
        testEvent.setDate("2016-06-25 19:31:57");
        ts.findEventTickets(testEvent);
    }

		@Test
		public void setNullEventTicket() {
			Event testEvent = new Event();
			testEvent.setId(1);
			ts.findEventTickets(testEvent).get(0).setUserId(0);
		}

		@Test
		public void setUserTicket() {
			Event testEvent = new Event();
			testEvent.setId(1);
			ts.findEventTickets(testEvent).get(0).setUserId(1);
		}

    @Test
    public void testUpdate() {
			Ticket testTicket = new Ticket();
			assertNull(ts.update(testTicket));
		}

}
