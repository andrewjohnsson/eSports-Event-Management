package by.bsuir.spp.ils.lab.entity;

import javax.persistence.*;

/**
 * Created by andrewjohnsson on 08.04.16.
 */
@Entity
@Table(name = "team_has_event", schema = "esports")
@IdClass(TeamHasEventPK.class)
public class TeamHasEvent {
  private int teamId;
  private int eventId;

  @Id
  @Column(name = "team_id")
  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  @Id
  @Column(name = "event_id")
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

    TeamHasEvent that = (TeamHasEvent) o;

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
