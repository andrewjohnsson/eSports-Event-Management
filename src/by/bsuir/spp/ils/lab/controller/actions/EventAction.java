package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.helper.PermissionHelper;
import by.bsuir.spp.ils.lab.service.AuthService;
import by.bsuir.spp.ils.lab.service.EventService;
import by.bsuir.spp.ils.lab.service.TicketService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class EventAction extends ActionSupport {
  private TicketService ticketService;
	private EventService eventService;
  private PermissionHelper permissionHelper;
  private AuthService authService;
  private Event event;
  private List<Event> events;
  private Map<Integer, List<Team>> participants;
	private Integer tickets;
	private String error;

  public EventAction(){
    permissionHelper = new PermissionHelper();
		ticketService = new TicketService();
    eventService = new EventService();
    authService = new AuthService();
		setError(null);
  }

  public String create(){
    if (permissionHelper.canAddEvent() || permissionHelper.isAdmin()) {
      try {
				for (int i = 0; i < this.getTickets(); i++) {
					Ticket ticket = new Ticket();
					ticket.setEventId(eventService.list().get(eventService.list().size()-1).getId());
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
		if (permissionHelper.isAdmin()) {
			eventService.delete(getEvent());
			ticketService.findEventTickets(getEvent()).forEach(ticket -> {
				ticketService.delete(ticket.getId());
			});
		}else{
			if(permissionHelper.canAddEvent()){
				if (authService.getCurrentUser().getEventId() == getEvent().getId()){
					eventService.delete(getEvent());
					ticketService.findEventTickets(getEvent()).forEach(ticket -> {
						ticketService.delete(ticket.getId());
					});
				}
			}else{
				setError("You Don't Have Enough Rights To Do That");
			}
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
