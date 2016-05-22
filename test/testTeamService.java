import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.service.TeamService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNull;

/**
 * Created by andrewjohnsson on 11.05.2016.
 */
public class testTeamService {

    public static TeamService ts;

    @BeforeClass
    public static void init() {
        ts = new TeamService();
    }

    @Test()
    public void listTeam() {
        ts.list();
    }

    @Test()
    public void testAddTeam() {
        Team testTeam = new Team();
        assertNull(ts.add(testTeam));
    }

    @Test(expected = Exception.class)
    public void testUpdateTeam() {
        Team testTeam = new Team();
        ts.update(testTeam);
    }

    @Test
    public void testDeleteTeam() {
        Team testTeam = new Team();
        Assert.assertTrue(ts.delete(testTeam));
    }

}
