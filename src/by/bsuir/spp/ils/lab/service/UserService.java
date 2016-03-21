package by.bsuir.spp.ils.lab.service;

import java.util.List;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.helper.JSONHelper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import org.hibernate.Transaction;

/**
 * Created by andrewjohnsson on 29.02.16.
 */
public class UserService extends HibernateUtil {
  private JSONHelper helper = new JSONHelper();
  private Session session;

  public UserService() {}

  public User add(User user) {
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      Transaction transaction = session.beginTransaction();
      try {
        session.save(user);
        transaction.commit();
      } catch (Exception e) {
        transaction.rollback();
        throw e;
      }
    } finally {
      HibernateUtil.closeSession();
    }
    return user;
  }

  public void delete(int id) {
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      Transaction transaction = session.beginTransaction();
      try {
        User user = (User) session.load(User.class, id);
        if (null != user) {
          session.delete(user);
        }
        transaction.commit();
      } catch (Exception e) {
        transaction.rollback();
        throw e;
      }
    } finally {
      HibernateUtil.closeSession();
    }
  }

  public List<User> find(User user) {
    session = HibernateUtil.getSessionFactory().openSession();
    List<User> users = null;
    try {
      Transaction transaction = session.beginTransaction();
      try {
        try {
          users = (List<User>) session.createQuery("from User where name = " + "'" + user.getName() + "'").list();
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
      HibernateUtil.closeSession();
    }
    return users;
  }

  public List<User> list() {
    session = HibernateUtil.getSessionFactory().openSession();
    List<User> users = null;
    try {
      Transaction transaction = session.beginTransaction();
      try {
        try {
          users = (List<User>) session.createQuery("from User").list();
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
      HibernateUtil.closeSession();
    }
    return users;
  }
}