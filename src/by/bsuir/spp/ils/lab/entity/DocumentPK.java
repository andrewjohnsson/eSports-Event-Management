package by.bsuir.spp.ils.lab.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by andrewjohnsson on 08.04.16.
 */
public class DocumentPK implements Serializable {
  private int id;
  private int userId;
  private int typeId;

  @Column(name = "id")
  @Id
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "user_id")
  @Id
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Column(name = "type_id")
  @Id
  public int getTypeId() {
    return typeId;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DocumentPK that = (DocumentPK) o;

    if (id != that.id) return false;
    if (userId != that.userId) return false;
    if (typeId != that.typeId) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + userId;
    result = 31 * result + typeId;
    return result;
  }
}
