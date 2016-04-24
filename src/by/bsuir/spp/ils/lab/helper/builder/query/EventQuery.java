package by.bsuir.spp.ils.lab.helper.builder.query;

import by.bsuir.spp.ils.lab.entity.Event;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class EventQuery {

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

  public String generateEventQuery(Event event){
    String query= "from Event";
    if (null != event.getName()){
      if (!event.getName().isEmpty()){
        query += this.getPrefix(query) + " name = " + "'" + event.getName() + "'";
      }
    }
    if (event.getId() > 0){
      query += this.getPrefix(query) + " team_id = " + "'" + event.getId() + "'";
    }
    if (event.getDate() != null){
      query += this.getPrefix(query) + " team_id = " + "'" + event.getDate() + "'";
    }
    return query;
  }

}
