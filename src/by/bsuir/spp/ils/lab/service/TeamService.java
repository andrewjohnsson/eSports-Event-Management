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
public class TeamService extends HibernateUtil {
  private Session session;
  private Transaction transaction;
  private List<Team> teams;
  private ParticipationService service;

  public TeamService(){
    service = new ParticipationService();
  }

  public Team add(Team team){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
        session.save(team);
        transaction.commit();
        return team;
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

  public List<Team> list(){
    session = HibernateUtil.getSessionFactory().openSession();
    transaction = session.beginTransaction();
    teams = (List<Team>) session.createQuery("from Team").list();
    service.setParticipations(teams);
    return teams;
  }

  public Map<Integer, List<Event>> getParticipations(){
    return service.getParticipations();
  }

  public Team update(Team team) {
    session = HibernateUtil.getSessionFactory().openSession();
    try{
      transaction = session.beginTransaction();
      try {
        session.update(team);
        transaction.commit();
        return team;
      }catch (HibernateException e){
        transaction.rollback();
        throw e;
      }
    }finally {
      session.close();
    }
  }

  public boolean delete(int id){
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
        Team team = (Team) session.load(Team.class, id);
        if (null != team) {
          session.delete(team);
          transaction.commit();
          return true;
        }
        transaction.commit();
      } catch (Exception e) {
        transaction.rollback();
        throw e;
      }
    } finally {
      session.close();
    }
    return false;
  }
}
