import by.bsuir.spp.ils.lab.entity.DocumentPK;
import by.bsuir.spp.ils.lab.entity.TeamHasEvent;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testTeamHasEvent {

    @Test
    public void getEventId(){
        TeamHasEvent teamHasEvent = new TeamHasEvent();
        teamHasEvent.setEventId(1);
        assertEquals(1, teamHasEvent.getEventId());
    }

    @Test
    public void setEventId(){
        TeamHasEvent teamHasEvent = new TeamHasEvent();
        teamHasEvent.setEventId(1);
        assertEquals(1, teamHasEvent.getEventId());
    }

    @Test
    public void getTeamId(){
        TeamHasEvent teamHasEvent = new TeamHasEvent();
        teamHasEvent.setTeamId(1);
        assertEquals(1, teamHasEvent.getTeamId());
    }

    @Test
    public void setTeamId(){
        TeamHasEvent teamHasEvent = new TeamHasEvent();
        teamHasEvent.setTeamId(1);
        assertEquals(1, teamHasEvent.getTeamId());
    }
}
