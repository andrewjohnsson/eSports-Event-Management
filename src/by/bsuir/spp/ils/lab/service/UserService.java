package by.bsuir.spp.ils.lab.service;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by andrewjohnsson on 29.02.16.
 */
public class UserService extends HibernateUtil{
  private Session session;
  private Transaction transaction;

  public UserService() {}

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

	public void delete(User user){
		session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			try {
				session.delete(user);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				throw e;
			}
		} finally {
			session.close();
		}
	}

  public User find(int id) {
    session = HibernateUtil.getSessionFactory().openSession();
    try {
      transaction = session.beginTransaction();
      try {
        try {
          User user = (User) session.createQuery("from User where id="+id).list().get(0);
          transaction.commit();
					return user;
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

	public User update(User user) {
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			try {
				session.update(user);
				transaction.commit();
        return user;
			}catch (HibernateException e){
				transaction.rollback();
				throw e;
			}
		}finally {
			session.close();
		}
	}

}