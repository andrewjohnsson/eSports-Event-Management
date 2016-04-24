package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.service.EventService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class EventAction extends ActionSupport {
  private EventService service = new EventService();
  private Event event;
  private List<Event> events;
  private Map<Integer, List<Team>> participants;

  public EventAction(){}

  public String create(){
    try {
      this.event = service.add(getEvent());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String read(){
    try {
      this.events = service.list();
      this.participants = service.getParticipants();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String update(){
    try {
      this.events = service.list();        //TO-DO Find impl here
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String delete(){
    service.delete(getEvent().getId());
    return SUCCESS;
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
