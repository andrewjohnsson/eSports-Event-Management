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
  private TeamService service;
  private PermissionHelper helper;
  private Team team;
  private List<Team> teams;
  private Map<Integer, List<Event>> participations;
  private String error;

  public TeamAction(){
		service = new TeamService();
    helper = new PermissionHelper();
		setError(null);
  }

  public String create(){
    if (helper.canCreateTeam() || helper.isAdmin()){
      try {
        this.team = service.add(getTeam());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else{
			setError("You Don't Have Enough Rights To Do That");
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

  public String list() {
    try {
      this.teams = service.list();
      this.participations = service.getParticipations();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String update(){
		if (helper.isAdmin()){
			try {
				this.team = service.update(getTeam());
			} catch (Exception e) {
				e.printStackTrace();
			}
    }else{
			setError("You Should Be A Manager Of This Team Or Be An Admin");
		}
    return SUCCESS;
  }

  public String delete() {
    if (helper.canCreateTeam() || helper.isAdmin()) {
      service.delete(getTeam());
      return SUCCESS;
    }else{
			setError("You Don't Have Enough Rights To Do That");
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

	public String getError() { return error; }

	public void setError(String error) { this.error = error; }
}
