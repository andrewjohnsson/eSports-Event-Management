package by.bsuir.spp.ils.lab.helper.builder.query;

import by.bsuir.spp.ils.lab.entity.Team;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class TeamQuery {

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

  public String generateTeamQuery(Team team){
    String query= "from Team";
    if (null != team.getName()){
      if (!team.getName().isEmpty()){
        query += this.getPrefix(query) + " name = " + "'" + team.getName() + "'";
      }
    }
    if (team.getId() > 0){
      query += this.getPrefix(query) + " team_id = " + "'" + team.getId() + "'";
    }
    if (team.getUserId() > 0){
      query += this.getPrefix(query) + " team_id = " + "'" + team.getUserId() + "'";
    }
    return query;
  }
}
