package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.helper.PermissionHelper;
import by.bsuir.spp.ils.lab.service.TeamService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class TeamAction extends ActionSupport {
  private TeamService service = new TeamService();
  private PermissionHelper helper;
  private Team team;
  private List<Team> teams;
  private Map<Integer, List<Event>> participations;

  public TeamAction(){
    helper = new PermissionHelper();
  }

  public String create(){
    if (helper.canCreateTeam() || helper.isAdmin()){
      try {
        this.team = service.add(getTeam());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return SUCCESS;
    }
    return ERROR;
  }

  public String read() {
    try {
      this.teams = service.list();
      this.participations = service.getParticipations();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String update(){
    if (helper.canCreateTeam() || helper.isAdmin()){
      try {
        this.teams = service.list();        //TO-DO Find impl here
      } catch (Exception e) {
        e.printStackTrace();
      }
      return SUCCESS;
    }
    return ERROR;
  }

  public String delete() {
    if (helper.canCreateTeam() || helper.isAdmin()) {
      service.delete(getTeam().getId());
      return SUCCESS;
    }
    return ERROR;
  }

  public Team getTeam() { return this.team;}

  public void setTeam(Team team) {
    this.team = team;
  }

  public List<Team> getTeams() {
    return teams;
  }

  public Map<Integer, List<Event>> getParticipations() {return participations;}
}
