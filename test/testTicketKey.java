import by.bsuir.spp.ils.lab.entity.TicketPK;
import by.bsuir.spp.ils.lab.helper.MD5Helper;
import com.opensymphony.xwork2.ActionSupport;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testTicketKey {

    @Test
    public void getId(){
        TicketPK ticketPK = new TicketPK();
        ticketPK.setId(1);
        assertEquals(1, ticketPK.getId());
    }

    @Test
    public void setId(){
        TicketPK ticketPK = new TicketPK();
        ticketPK.setId(1);
        assertEquals(1, ticketPK.getId());
    }

    @Test
    public void getEventId(){
        TicketPK ticketPK = new TicketPK();
        ticketPK.setEventId(1);
        assertEquals(1, ticketPK.getEventId());
    }

    @Test
    public void setEventId(){
        TicketPK ticketPK = new TicketPK();
        ticketPK.setEventId(1);
        assertEquals(1, ticketPK.getEventId());
    }
}
