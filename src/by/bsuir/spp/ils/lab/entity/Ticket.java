package by.bsuir.spp.ils.lab.entity;

import javax.persistence.*;

/**
 * Created by andrewjohnsson on 08.04.16.
 */
@Entity
@IdClass(TicketPK.class)
public class Ticket {
  private int id;
  private Integer price;
  private String seat;
  private Integer userId;
  private int eventId;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "price")
  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  @Basic
  @Column(name = "seat")
  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  @Basic
  @Column(name = "user_id")
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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

    Ticket ticket = (Ticket) o;

    if (id != ticket.id) return false;
    if (eventId != ticket.eventId) return false;
    if (price != null ? !price.equals(ticket.price) : ticket.price != null) return false;
    if (seat != null ? !seat.equals(ticket.seat) : ticket.seat != null) return false;
    if (userId != null ? !userId.equals(ticket.userId) : ticket.userId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (seat != null ? seat.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    result = 31 * result + eventId;
    return result;
  }
}
