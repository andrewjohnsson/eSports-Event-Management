package by.bsuir.spp.ils.lab.entity;

import javax.persistence.*;

/**
 * Created by andrewjohnsson on 08.04.16.
 */
@Entity
@IdClass(DocumentPK.class)
public class Document {
  private int id;
  private int userId;
  private Integer inheritId;
  private String series;
  private Integer number;
  private String nationality;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Id
  @Column(name = "user_id")
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Document document = (Document) o;

    if (id != document.id) return false;
    return userId == document.userId;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + userId;
    return result;
  }

  @Basic
  @Column(name = "inherit_id")
  public Integer getInheritId() {
    return inheritId;
  }

  public void setInheritId(Integer inheritId) {
    this.inheritId = inheritId;
  }

  @Basic
  @Column(name = "series")
  public String getSeries() {
    return series;
  }

  public void setSeries(String series) {
    this.series = series;
  }

  @Basic
  @Column(name = "number")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  @Basic
  @Column(name = "nationality")
  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
}
