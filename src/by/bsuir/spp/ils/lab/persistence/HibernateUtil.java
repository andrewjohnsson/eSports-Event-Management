package by.bsuir.spp.ils.lab.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
/**
 * Created by andrewjohnsson on 01.03.16.
 */
public class HibernateUtil {
  private static final ThreadLocal<Session> threadLocal = new ThreadLocal();
  private static SessionFactory ourSessionFactory;
  private static ServiceRegistry serviceRegistry;

  static {
    try {
      configureSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static SessionFactory configureSessionFactory() {
    try {

      Configuration configuration = new Configuration();
      configuration.configure();
      serviceRegistry = new ServiceRegistryBuilder()
              .applySettings(configuration.getProperties())
              .buildServiceRegistry();
      ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);

      return ourSessionFactory;
    } catch (HibernateException e) {
      e.printStackTrace();
    }
    return ourSessionFactory;
  }

  public static void rebuildSessionFactory() {
    try {
      ourSessionFactory = configureSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static SessionFactory getSessionFactory() {
    return ourSessionFactory;
  }

  public static Session getSession() throws HibernateException {
    Session session = threadLocal.get();

    if (session == null || !session.isOpen()) {
      if (ourSessionFactory == null) {
        rebuildSessionFactory();
      }
      session = (ourSessionFactory != null) ? ourSessionFactory.openSession() : null;
      threadLocal.set(session);
    }

    return session;
  }

  public static void closeSession() throws HibernateException {
    Session session = (Session) threadLocal.get();
    threadLocal.set(null);

    if (session != null) {
      session.close();
    }
  }
}
