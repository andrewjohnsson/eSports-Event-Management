package by.bsuir.spp.ils.lab.controller.action.user;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.helper.JSONHelper;
import by.bsuir.spp.ils.lab.service.UserService;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by andrewjohnsson on 28.02.16.
 */
public class UserAction extends ActionSupport{
  private UserService service;
  private List<User> users;
  private User user;
  private int id;
  JSONHelper helper;

  public UserAction(){
    service = new UserService();
    helper = new JSONHelper();
  }

  public String create() {
    try {
      this.user = service.add(getUser());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String read() {
    try {
      this.users = service.find(getUser());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Action.SUCCESS;
  }

  public String update() {
    this.users = service.list();
    return SUCCESS;
  }

  public String delete() {
    service.delete(getId());
    return SUCCESS;
  }


  public String list(){
    try {
      this.users = service.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public List<User> getUsers() {
    return users;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getUser() { return this.user;}

  public void setUser(User person) {
    this.user = person;
  }
}
