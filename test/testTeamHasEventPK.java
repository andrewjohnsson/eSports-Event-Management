import by.bsuir.spp.ils.lab.entity.TeamHasEventPK;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testTeamHasEventPK {

    @Test
    public void getEventId(){
        TeamHasEventPK teamHasEvent = new TeamHasEventPK();
        teamHasEvent.setEventId(1);
        assertEquals(1, teamHasEvent.getEventId());
    }

    @Test
    public void setEventId(){
        TeamHasEventPK teamHasEvent = new TeamHasEventPK();
        teamHasEvent.setEventId(1);
        assertEquals(1, teamHasEvent.getEventId());
    }

    @Test
    public void getTeamId(){
        TeamHasEventPK teamHasEvent = new TeamHasEventPK();
        teamHasEvent.setTeamId(1);
        assertEquals(1, teamHasEvent.getTeamId());
    }

    @Test
    public void setTeamId(){
        TeamHasEventPK teamHasEvent = new TeamHasEventPK();
        teamHasEvent.setTeamId(1);
        assertEquals(1, teamHasEvent.getTeamId());
    }
}
