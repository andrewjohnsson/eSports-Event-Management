package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.helper.PermissionHelper;
import by.bsuir.spp.ils.lab.service.AuthService;
import by.bsuir.spp.ils.lab.service.EventService;
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

  public EventAction(){
    permissionHelper = new PermissionHelper();
    eventService = new EventService();
    userService = new UserService();
    authService = new AuthService();
  }

  public String create(){
    if (permissionHelper.canAddEvent() || permissionHelper.isAdmin()) {
      try {
        this.event = eventService.add(getEvent());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return SUCCESS;
    }
    return ERROR;
  }

  public String read(){
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
        this.events = eventService.list();        //TO-DO Find impl here
      } catch (Exception e) {
        e.printStackTrace();
      }
      return SUCCESS;
    }
    return ERROR;
  }

  public String delete(){
    if (permissionHelper.canAddEvent() || permissionHelper.isAdmin()) {
      if (userService.find(authService.getCurrentUser()).get(0).getEventId() == getEvent().getId()){
        eventService.delete(getEvent().getId());
        return SUCCESS;
      }
    }
    return ERROR;
  }

  public Event getEvent() { return this.event;}

  public void setEvent(Event team) {
    this.event = team;
  }

  public List<Event> getEvents() {
    return events;
  }

  public Map<Integer, List<Team>> getParticipants() {return participants;}
}
