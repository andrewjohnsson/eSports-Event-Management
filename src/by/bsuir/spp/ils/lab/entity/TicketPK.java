package by.bsuir.spp.ils.lab.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by andrewjohnsson on 08.04.16.
 */
public class TicketPK implements Serializable {
  private int id;
  private int eventId;

  @Column(name = "id")
  @Id
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "event_id")
  @Id
  public int getEventId() {
    return eventId;
  }

  public void setEventId(int eventId) {
    this.eventId = eventId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TicketPK ticketPK = (TicketPK) o;

    if (id != ticketPK.id) return false;
    if (eventId != ticketPK.eventId) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + eventId;
    return result;
  }
}
