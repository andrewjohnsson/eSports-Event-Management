package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.helper.PermissionHelper;
import by.bsuir.spp.ils.lab.service.AuthService;
import by.bsuir.spp.ils.lab.service.EventService;
import by.bsuir.spp.ils.lab.service.TicketService;
import by.bsuir.spp.ils.lab.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class EventAction extends ActionSupport {
  private EventService eventService;
  private UserService userService;
  private PermissionHelper permissionHelper;
  private AuthService authService;
  private Event event;
  private List<Event> events;
  private Map<Integer, List<Team>> participants;
	private Integer tickets;
	private String error;

  public EventAction(){
    permissionHelper = new PermissionHelper();
    eventService = new EventService();
    userService = new UserService();
    authService = new AuthService();
		setError(null);
  }

  public String create(){
    if (permissionHelper.canAddEvent() || permissionHelper.isAdmin()) {
      try {
				TicketService ticketService = new TicketService();
				for (int i = 0; i < this.getTickets(); i++) {
					Ticket ticket = new Ticket();
					ticket.setEventId(this.getEvent().getId());
					ticket.setSeat(((Integer) (i+1)).toString());
					ticketService.add(ticket);
				}
				this.event = eventService.add(getEvent());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else{
			setError("You Don't Have Enough Rights To Do That");
		}
		return SUCCESS;
  }

  public String list(){
    try {
      this.events = eventService.list();
      this.participants = eventService.getParticipants();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String update(){
    if (permissionHelper.canAddEvent() || permissionHelper.isAdmin()) {
      try {
        this.event = eventService.update(getEvent());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else{
			setError("You Don't Have Enough Rights To Do That");
		}
		return SUCCESS;
  }

  public String delete(){
    if (permissionHelper.canAddEvent() || permissionHelper.isAdmin()) {
      if (userService.find(authService.getCurrentUser().getId()).getEventId() == getEvent().getId()){
        eventService.delete(getEvent().getId());
      }
    }else{
			setError("You Don't Have Enough Rights To Do That");
		}
		return SUCCESS;
  }

  public Event getEvent() { return this.event;}

  public void setEvent(Event team) {
    this.event = team;
  }

	public Integer getTickets() {
		return this.tickets;
	}

	public void setTickets(Integer ticketCount){
		this.tickets = ticketCount;
	}

  public List<Event> getEvents() {
    return events;
  }

  public Map<Integer, List<Team>> getParticipants() {return participants;}

	public String getError() { return error; }

	public void setError(String error) { this.error = error; }
}
