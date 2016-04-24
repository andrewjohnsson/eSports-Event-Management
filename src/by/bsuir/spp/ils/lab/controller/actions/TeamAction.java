package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.service.TeamService;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class TeamAction extends ActionSupport {
  private TeamService service = new TeamService();
  private Team team;
  private List<Team> teams;
  private Map<Integer, List<Event>> participations;

  public TeamAction(){}

  public String create(){
    try {
      this.team = service.add(getTeam());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
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
    try {
      this.teams = service.list();        //TO-DO Find impl here
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String delete() {
    service.delete(getTeam().getId());
    return SUCCESS;
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
