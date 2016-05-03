package by.bsuir.spp.ils.lab.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
@Entity
public class User {
  private int id;
  private Integer age;
  private Integer teamId;
  private Integer eventId;
  private boolean isbusy;
  private String name;
  private String password;
  private String email;
  private boolean isAdmin;
  private boolean isPlayer;
  private boolean isManager;
  private boolean isSupervisor;
  private boolean isViewer;
	private byte[] permissions;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "age")
  public Integer getAge() {
    return age;
    }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Basic
  @Column(name = "team_id")
  public Integer getTeamId() {
    return teamId;
  }

  public void setTeamId(Integer teamId) {
    this.teamId = teamId;
  }

  @Basic
  @Column(name = "event_id")
  public Integer getEventId() {
    return eventId;
  }

  public void setEventId(Integer eventId) {
    this.eventId = eventId;
  }

  @Basic
  @Column(name = "isbusy")
  public boolean getIsbusy() {
    return isbusy;
  }

  public void setIsbusy(boolean isbusy) {
    this.isbusy = isbusy;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "isAdmin")
  public boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(boolean admin) {
    isAdmin = admin;
  }

  @Basic
  @Column(name = "isPlayer")
  public boolean getIsPlayer() {
    return isPlayer;
  }

  public void setIsPlayer(boolean player) {
    isPlayer = player;
  }

  @Basic
  @Column(name = "isManager")
  public boolean getIsManager() {
    return isManager;
  }

  public void setIsManager(boolean manager) {
    isManager = manager;
  }

  @Basic
  @Column(name = "isSupervisor")
  public boolean getIsSupervisor() {
    return isSupervisor;
  }

  public void setIsSupervisor(boolean supervisor) {
    isSupervisor = supervisor;
  }

  @Basic
  @Column(name = "isViewer")
  public boolean getIsViewer() {
    return isViewer;
  }

  public void setIsViewer(boolean viewer) {
    isViewer = viewer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != user.id) return false;
    if (isbusy != user.isbusy) return false;
    if (age != null ? !age.equals(user.age) : user.age != null) return false;
    if (teamId != null ? !teamId.equals(user.teamId) : user.teamId != null) return false;
    if (eventId != null ? !eventId.equals(user.eventId) : user.eventId != null) return false;
    if (name != null ? !name.equals(user.name) : user.name != null) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
		return email != null ? email.equals(user.email) : user.email == null;

	}

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (age != null ? age.hashCode() : 0);
    result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
    result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }
}
