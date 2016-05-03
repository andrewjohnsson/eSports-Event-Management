package by.bsuir.spp.ils.lab.helper.builder.query;

import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.User;

/**
 * Created by andrewjohnsson on 29/04/16.
 * Helper class for generating queries for Hibernate
 */

public class TicketQuery {
    /**
     * Generating query, that finding by all fields in Ticket
     * @param ticket Finding ticket
     * @return Query form Hibernate
     */
    public String generateTicketQuery(Ticket ticket){
        String query = " from Ticket";
        if(ticket.getId() != 0){
            query += this.getPrefix(query) + " id = " + "'" + ticket.getId() + "'";
        }

        if(ticket.getEventId() != 0){
            query += this.getPrefix(query) + " event_id = " + "'" + ticket.getEventId() + "'";
        }

        if(ticket.getSeat() != null){
            if(!ticket.getSeat().isEmpty()) {
                query += this.getPrefix(query) + " seat = " + "'" + ticket.getSeat() + "'";
            }
        }

        if(ticket.getUserId() != 0){
            query += this.getPrefix(query) + " user_id = " +"'" + ticket.getUserId() + "'";
        }

        return query;
    }

    /**
     * Generating query, that can find all tickets of certain Event
     * @param event Finding Event
     * @return Query for Hibernate
     */
    public String generateTicketWithEventQuery(Event event){
        String query= "from Ticket";
        query += " where event_id = " + "'" + event.getId();
        return query;
    }

    /**
     * Query for getting all certain's user tickets
     * @param user Finding User
     * @return Query for Hibernate
     */
    public String generateTicketWithUserQuery(User user){
        String query = "from Ticket";
        query += " where user_id = " + "'" + user.getId();
        return query;
    }

    /**
     * Prefix for correct working of {@link TicketQuery#generateTicketQuery(Ticket)}
     * @param query Checking query
     * @return query with correct prefix
     */
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
}
