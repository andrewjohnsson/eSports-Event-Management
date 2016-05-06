package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.helper.PermissionHelper;
import by.bsuir.spp.ils.lab.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by andrewjohnsson on 28.02.16.
 */
public class UserAction extends ActionSupport{
  private UserService userService;
  private String error;
  private PermissionHelper helper;
  private List<User> users;
  private User user;

  public UserAction(){
    userService = new UserService();
    helper = new PermissionHelper();
		setError(null);
  }

  public String create() {
    if (helper.isAdmin()) {
      try {
        this.user = userService.add(getUser());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else{
			setUser(null);
			setError("You Don't Have Enough Rights To Do That");
		}
		return SUCCESS;
  }

  public String read() {
    try {
      this.users = userService.find(getUser());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Action.SUCCESS;
  }

  public String delete() {
    if (helper.isAdmin()) {
      userService.delete(getUser().getId());
    }else{
			setError("You Don't Have Enough Rights To Do That");
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

	public String update(){
    if (helper.isAdmin()) {
      if (this.getUser() != null) {
        this.user = userService.update(this.getUser());
      }
    }else{
			setUser(null);
			setError("You Don't Have Enough Rights To Do That");
		}
		return SUCCESS;
	}

  public List<User> getUsers() { return users; }

  public User getUser() { return user; }

  public void setUser(User person) {
    this.user = person;
  }

  public String getError() { return error; }

  public void setError(String error) { this.error = error; }
}
