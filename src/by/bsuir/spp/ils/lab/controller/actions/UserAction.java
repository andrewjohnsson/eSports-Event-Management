package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.helper.PermissionHelper;
import by.bsuir.spp.ils.lab.service.AuthService;
import by.bsuir.spp.ils.lab.service.UserService;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by andrewjohnsson on 28.02.16.
 */
public class UserAction extends ActionSupport{
  private UserService userService;
  //private AuthService authService;
  private PermissionHelper helper;
  private List<User> users;
  private User user;

  public UserAction(){
    userService = new UserService();
    //authService = new AuthService();
    helper = new PermissionHelper();
  }

  public String create() {
    if (helper.isAdmin()) {
      try {
        this.user = userService.add(getUser());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return SUCCESS;
    }
    return ERROR;
  }

  public String read() {
    try {
      this.users = userService.find(getUser());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Action.SUCCESS;
  }

  public String update() {
    if (helper.isAdmin()) {
      this.users = userService.list();
    }else{
      return ERROR;
    }
    return SUCCESS;
  }

  public String delete() {
    if (helper.isAdmin()) {
      userService.delete(getUser().getId());
    }
    return SUCCESS;
  }


  public String list(){
    try {
      this.users = userService.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public List<User> getUsers() {
    return users;
  }

  public User getUser() { return user; }

  public void setUser(User person) {
    this.user = person;
  }
}
