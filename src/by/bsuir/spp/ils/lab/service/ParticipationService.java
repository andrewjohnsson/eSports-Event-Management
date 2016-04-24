package by.bsuir.spp.ils.lab.service;

import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.TeamHasEvent;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 12.04.16.
 */
public class ParticipationService extends HibernateUtil {

  private Session session;
  private Transaction transaction;

  private List<Event> participationsInfo = new ArrayList<Event>();
  private List<TeamHasEvent> events;
  private List<Event> eventInfo;
  private Map<Integer, List<Event>> participations = new HashMap<Integer, List<Event>>();

  private List<Team> participantInfo, participantsInfo = new ArrayList<Team>();
  private List<TeamHasEvent> teams;
  private Map<Integer, List<Team>> participants = new HashMap<Integer, List<Team>>();

  public Map<Integer, List<Event>> getParticipations(){
    return this.participations;
  }

  public void setParticipations(List<Team> teams){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      teams.forEach(team -> {
        participationsInfo = new ArrayList<>();
        events = session.createQuery("from TeamHasEvent where teamId =" + team.getId()).list();
        events.forEach(event -> {
          eventInfo = session.createQuery("from Event where id =" + event.getEventId()).list();
          if (!eventInfo.isEmpty()) {
            eventInfo.forEach(event1 -> {
              participationsInfo.add(event1);
            });
          }
        });
        this.participations.put(team.getId(), participationsInfo);
        participationsInfo = null;
      });
    }
    finally {
      session.close();
    }
  }

  public Map<Integer, List<Team>> getParticipants(){
    return this.participants;
  }

  public void setParticipants(List<Event> events){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      events.forEach(event -> {
        participantsInfo = new ArrayList<>();
        teams = session.createQuery("from TeamHasEvent where eventId =" + event.getId()).list();
        teams.forEach(team -> {
          participantInfo = session.createQuery("from Team where id =" + team.getTeamId()).list();
          participantsInfo.add(participantInfo.get(0));
        });
        this.participants.put(event.getId(), participantsInfo);
        participantsInfo = null;
      });
    }
    finally {
      session.close();
    }
  }
}
