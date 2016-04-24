package by.bsuir.spp.ils.lab.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by andrewjohnsson on 08.04.16.
 */
public class TeamHasEventPK implements Serializable {
  private int teamId;
  private int eventId;

  @Column(name = "team_id")
  @Id
  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
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

    TeamHasEventPK that = (TeamHasEventPK) o;

    if (teamId != that.teamId) return false;
    if (eventId != that.eventId) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = teamId;
    result = 31 * result + eventId;
    return result;
  }
}
