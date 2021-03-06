package by.bsuir.spp.ils.lab.service;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class AuthService extends HibernateUtil implements SessionAware {
  private User currentUser;
  private Session session;
  private Map authSession;

  public AuthService(){
    this.authSession = ActionContext.getContext().getSession();
  }

  public User check(String email, String password){
    if ((null != email) && (null != password)){
      if ((!email.isEmpty()) && (!password.isEmpty())){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
          Transaction transaction = session.beginTransaction();
          try {
            User temp = (User) session.createQuery("from User where email = " + "'" + email + "' And password  = " + "'" + password + "'").list().get(0);
            transaction.commit();
            if (temp != null){
              setCurrentUser(temp);
              return getCurrentUser();
            }
          }catch (HibernateException e){
            transaction.rollback();
            throw e;
          }
        }finally {
          HibernateUtil.closeSession();
        }
      }
    }
    return null;
  }

  public Map setUserLogin(int id){
    this.authSession.put("id", id);
    return this.authSession;
  }

  public int getUserId(){
    if (this.authSession.get("id") != null)
      return (int) this.authSession.get("id");
    return 0;
  }

  public String getUserPermissions(){
    if (this.authSession.get("permissions") != null)
      return (String) this.authSession.get("permissions");
    return null;
  }

  public void setUserPermissions(byte[] value){this.authSession.put("permissions", value); }

  public User getCurrentUser(){
    return this.currentUser;
  }

  public void setCurrentUser(User user){
    this.currentUser = user;
  }

  public void logout(){
    this.authSession.clear();
    setCurrentUser(null);
  }

  public void setSession(Map<String, Object> sessionMap) {
    this.authSession = authSession;
  }
}
