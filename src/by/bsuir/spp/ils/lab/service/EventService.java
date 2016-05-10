package by.bsuir.spp.ils.lab.service;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Team;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class EventService extends HibernateUtil {
  private TicketService ticketService;
  private Session session;
  private Transaction transaction;
  private ParticipationService participationService;
  private List<Event> events;

  public EventService(){
		ticketService = new TicketService();
		participationService = new ParticipationService();
  }

  public Event add(Event event){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
        session.save(event);
        if (!transaction.wasCommitted()) {
          transaction.commit();
        }
        return event;
      }
      catch (HibernateException e){
        e.printStackTrace();
        transaction.rollback();
      }
    }
    catch (Exception e){
      transaction.rollback();
    }
    finally {
      if (session.isOpen()){
        session.close();
      }
    }
    return null;
  }

  public List<Event> list(){
    session = HibernateUtil.getSessionFactory().openSession();
    transaction = session.beginTransaction();
    events = (List<Event>) session.createQuery("from Event").list();
		participationService.setParticipants(events);
    return events;
  }

  public Map<Integer, List<Team>> getParticipants(){
    return participationService.getParticipants();
  }

  public Event update(Event event){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
        session.update(event);
        if (!transaction.wasCommitted()) {
          transaction.commit();
        }
        return event;
      }
      catch (HibernateException e){
        e.printStackTrace();
        transaction.rollback();
      }
    }
    catch (Exception e){
      transaction.rollback();
    }
    finally {
      session.close();
    }
    return null;
  }

  public boolean delete(Event event){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
				session.delete(event);
				if (!transaction.wasCommitted()) {
					transaction.commit();
				}
				return true;
			}
      catch (Exception e) {
        transaction.rollback();
        throw e;
      }
    } finally {
      session.close();
    }

  }
}
