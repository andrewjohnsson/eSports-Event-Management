package by.bsuir.spp.ils.lab.service;

import by.bsuir.spp.ils.lab.entity.Event;
import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrewjohnsson on 29/04/16.
 */
public class TicketService extends HibernateUtil {

    private Session session;
    private Transaction transaction;

    public Ticket add(Ticket ticket){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            try {
                session.save(ticket);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                throw e;
            }
        } finally{
            session.close();
        }
        return null;
    }

    public List<Ticket> list(){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Ticket> tickets = (List<Ticket>) session.createQuery("from Ticket").list();
        return tickets;
    }

    public List<Ticket> findUserTickets(User user){
        List<Ticket> tickets = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            try {
                try {
                    tickets = (List<Ticket>) session.createQuery("from Ticket where userId="+user.getId()).list();
                    transaction.commit();
                } catch (HibernateException e) {
                    e.printStackTrace();
                    transaction.rollback();
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            session.close();
        }

        return tickets;
    }

    public List<Ticket> findEventTickets(Event event){
        List<Ticket> tickets = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            try {
                try {
                    tickets = (List<Ticket>) session.createQuery("from Ticket where eventId="+event.getId()).list();
                    transaction.commit();
                } catch (HibernateException e) {
                    e.printStackTrace();
                    transaction.rollback();
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } finally {
            session.close();
        }

        return tickets;
    }

    public Ticket update(Ticket ticket){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            try {
                session.update(ticket);
                if (!transaction.wasCommitted()) {
                    transaction.commit();
                }
                return ticket;
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

    public boolean delete(int id){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            try {
                Ticket ticket = (Ticket) session.load(Ticket.class, id);
                if (null != ticket) {
                    session.delete(ticket);
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
