package by.bsuir.spp.ils.lab.helper;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import by.bsuir.spp.ils.lab.service.AuthService;
import com.sun.tools.javac.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class PermissionHelper extends HibernateUtil {

  private Session session;
  private Transaction transaction;
  private AuthService service;

  public PermissionHelper(){
    service = new AuthService();
  }

  private void UpdateStatus(){
    session = HibernateUtil.getSessionFactory().openSession();
    transaction = session.beginTransaction();
    List<User> users = (List<User>) session.createQuery("from User where id ="+service.getUserId()).list();
    service.setUserPermissions(users.get(0).getPermissions());
  }

  public boolean canWatchGames(){
    UpdateStatus();
    if(service.getUserPermissions()[0] == 49) {
      return true;
    }
    return false;
  }
  public boolean canPlayGames(){
    UpdateStatus();
    if(service.getUserPermissions()[1] == 49) {
      return true;
    }
    return false;
  }
  public boolean canCreateTeam(){
    UpdateStatus();
    if(service.getUserPermissions()[2] == 49) {
      return true;
    }
    return false;
  }
  public boolean canAddEvent(){
    UpdateStatus();
    if(service.getUserPermissions()[3] == 49) {
      return true;
    }
    return false;
  }
  public boolean isAdmin(){
    UpdateStatus();
    if(service.getUserPermissions()[4] == 49) {
      return true;
    }
    return false;
  }
}
