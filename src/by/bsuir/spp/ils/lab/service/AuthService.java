package by.bsuir.spp.ils.lab.service;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.persistence.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by andrewjohnsson on 10.04.16.
 */
public class AuthService extends HibernateUtil implements SessionAware {
  private Session session;
  private Map authSession;

  public AuthService(){
    this.authSession = ActionContext.getContext().getSession();
  }

  public List<User> checkLoginState(String email, String password){
    List<User> userList;
    if ((null != email) && (null != password)){
      if ((!email.isEmpty()) && (!password.isEmpty())){
        session = HibernateUtil.getSessionFactory().openSession();
        try{
          Transaction transaction = session.beginTransaction();
          try {
            userList = session.createQuery("from User where email = " + "'" + email + "' And password  = " + "'" + password + "'").list();
            transaction.commit();
            if ((userList != null) && (!userList.isEmpty())){
              return userList;
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

  public Map setUserLogin(int id, byte[] permissions, String name){
    this.authSession.put("id", id);
    this.authSession.put("permissions", permissions);
    this.authSession.put("name", name);
    return this.authSession;
  }

  public int getUserId(){
    if (this.authSession.get("id") != null)
      return (int) this.authSession.get("id");
    return 0;
  }

  public byte[] getUserPermissions(){
    if (this.authSession.get("permissions") != null)
      return (byte[]) this.authSession.get("permissions");
    return null;
  }

  public void setUserPermissions(byte[] value){
    this.authSession.put("permissions", value);
  }

  public String getUserName(){
    if (this.authSession.get("name") != null)
      return this.authSession.get("name").toString();
    return "";
  }

  public void logOut(){
    this.authSession.clear();
  }

  public void setSession(Map<String, Object> sessionMap) {
    this.authSession = authSession;
  }
}
