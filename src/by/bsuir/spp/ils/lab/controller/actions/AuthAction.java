package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.service.AuthService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by andrewjohnsson on 11.04.16.
 */
public class AuthAction extends ActionSupport {
  private AuthService authService;
  private User user;
  private List<User> users;

  public AuthAction(){
    authService = new AuthService();
  }

  public String login(){
    String email, pass;
    if (authService.getUserId() != 0){return SUCCESS;}
    if (this.getUser() != null) {
      System.out.println(this.getUser().getEmail());
      email = this.getUser().getEmail();
      pass = this.getUser().getPassword();
      if (email.length() < 4 || pass.length() < 4) {
        System.out.println("Incorrect login or pass length");
        return ERROR;
      } else {
        users = authService.checkLoginState(email, pass);
        if (users != null) {
          authService.setUserLogin(users.get(0).getId(), users.get(0).getPermissions(), users.get(0).getName());
          return SUCCESS;
        } else
          System.out.println("No such user");
      }
    }
    return ERROR;
  }

  public User getUser() { return user; }

  public void setUser(User person) {
    this.user = person;
  }

  public List<User> getUsers() {
    return users;
  }
}
