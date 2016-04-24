package by.bsuir.spp.ils.lab.service;

import java.util.List;
import by.bsuir.spp.ils.lab.entity.User;

import by.bsuir.spp.ils.lab.helper.builder.query.UserQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import org.hibernate.Transaction;

/**
 * Created by andrewjohnsson on 29.02.16.
 */
public class UserService extends HibernateUtil{
  private Session session;
  private Transaction transaction;
  private UserQuery builder;

  public UserService() {
    this.builder = new UserQuery();
  }

  public User add(User user) {
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
        session.save(user);
        transaction.commit();
      } catch (Exception e) {
        transaction.rollback();
        throw e;
      }
    } finally {
      session.close();
    }
    return user;
  }

  public boolean delete(int id) {
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
        User user = (User) session.load(User.class, id);
        if (null != user) {
          session.delete(user);
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

  public List<User> find(User user) {
    session = HibernateUtil.getSessionFactory().openSession();

    List<User> users = null;
    try {
      transaction = session.beginTransaction();
      try {
        try {
          users = (List<User>) session.createQuery(this.builder.generateUserQuery(user)).list();
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
    return users;
  }

  public List<User> list() {
    session = HibernateUtil.getSessionFactory().openSession();
    List<User> users;
    try {
      transaction = session.beginTransaction();
      try {
        try {
          users = (List<User>) session.createQuery("from User").list();
          transaction.commit();
          return users;
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
    return null;
  }
}