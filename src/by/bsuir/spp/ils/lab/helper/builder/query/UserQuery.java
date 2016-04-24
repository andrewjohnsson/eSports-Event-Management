package by.bsuir.spp.ils.lab.helper.builder.query;

import by.bsuir.spp.ils.lab.entity.User;

import java.util.Arrays;

/**
 * Created by andrewjohnsson on 03.04.16.
 */
public class UserQuery {

  private String getPrefix(String query){
    String prefix;
    if (!query.endsWith("'")){
      prefix = " where";
    }
    else{
      prefix = " and";
    }
    return prefix;
  }

  public String generateUserQuery(User user){
    String query= "from User";
    if (null != user.getName()){
      if (!user.getName().isEmpty()){
        query += this.getPrefix(query) + " name = " + "'" + user.getName() + "'";
      }
    }
    if (null != user.getLogin()) {
      if (!user.getLogin().isEmpty()){
        query += this.getPrefix(query) + " login = " + "'" + user.getLogin() + "'";
      }
    }
    if (null != user.getPassword()){
      if (!user.getPassword().isEmpty()) {
        query += this.getPrefix(query) + " password = " + "'" + user.getPassword() + "'";
      }
    }
    if (null != user.getEmail()){
      if (!user.getEmail().isEmpty()){
        query += this.getPrefix(query) + " email = " + "'" + user.getEmail() + "'";
      }
    }
    if (user.getAge() != null) {
      if (user.getAge() > 0) {
        query += this.getPrefix(query) + " age = " + "'" + user.getAge() + "'";
      }
    }
    if (null != user.getTeamId()) {
      if (user.getTeamId() > 0){
        query += this.getPrefix(query) + " team_id = " + "'" + user.getTeamId() + "'";
      }
    }
    if (null != user.getEventId()) {
      if (user.getEventId() > 0){
        query += this.getPrefix(query) + " event_id = " + "'" + user.getEventId() + "'";
      }
    }
    if (user.getPermissions() != null) {
      if (user.getPermissions().length > 0) {
        query += this.getPrefix(query) + " permissions = " + "'" + user.getPermissions() + "'";
      }
    }
    return query;
  }

}
