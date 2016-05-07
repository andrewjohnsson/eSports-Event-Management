package by.bsuir.spp.ils.lab.helper;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import by.bsuir.spp.ils.lab.service.AuthService;
import org.hibernate.Session;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class PermissionHelper extends HibernateUtil {
  private Session session;
  private AuthService service;

  public PermissionHelper(){
    service = new AuthService();
  }

  public User UpdateUser(){
    this.session = HibernateUtil.getSessionFactory().openSession();
    try {
			User user = (User) session.createQuery("from User where id ="+service.getUserId()).list().get(0);
			byte[] newPermissions = new byte[]{
				(byte) (user.getIsViewer() ? 1 : 0),
				(byte) (user.getIsPlayer() ? 1 : 0),
				(byte) (user.getIsManager() ? 1 : 0),
				(byte) (user.getIsSupervisor() ? 1 : 0),
				(byte) (user.getIsAdmin() ? 1 : 0)
			};
			service.setUserPermissions(newPermissions);
			return user;
    }catch(Exception e){}
   return null;
  }

  public boolean canWatchGames(){
    return UpdateUser().getIsViewer();
  }
  public boolean canPlayGames(){
    return UpdateUser().getIsPlayer();
  }
  public boolean canCreateTeam(){
    return UpdateUser().getIsManager();
  }
  public boolean canAddEvent(){
    return UpdateUser().getIsSupervisor();
  }
  public boolean isAdmin(){
    return UpdateUser().getIsAdmin();
  }
}
