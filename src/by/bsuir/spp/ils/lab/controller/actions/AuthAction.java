package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.service.AuthService;
import by.bsuir.spp.ils.lab.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by andrewjohnsson on 11.04.16.
 */
public class AuthAction extends ActionSupport {
  private AuthService authService;
	private UserService userService;
  private User user;
	private String error;

  public AuthAction(){
		authService = new AuthService();
		userService = new UserService();
  }

  public String login(){
    String email, pass;
    if (this.getUser() != null) {
      email = this.getUser().getEmail();
      pass = this.getUser().getPassword();
      if (email.length() < 4 || pass.length() < 4) {
        System.out.println("Incorrect login or pass length");
        return ERROR;
      } else {
				try {
					setUser(authService.isLogged(email, pass));
					authService.setUserLogin(getUser().getId());
					return SUCCESS;
				}catch (Exception e){
					user = null;
					setError("Cannot login!");
					System.out.println("No such user");
					return SUCCESS;
				}
      }
    }
    return SUCCESS;
  }

	public String register(){
		if ((getUser() != null) && authService.getUserId() == 0) {
			try {
				this.user = userService.add(getUser());
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String check(){
		int id = authService.getUserId();
		if (id != 0){
			User temp = new User();
			temp.setId(id);
			this.setUser(userService.find(temp).get(0));
			return SUCCESS;
		}
		setError("You are not logged in!");
		return SUCCESS;
	}

	public String logout(){
		authService.logout();
		return SUCCESS;
	}

  public User getUser() { return user; }

  public void setUser(User person) {
    this.user = person;
  }

	public String getError() { return error; }

	public void setError(String error) { this.error = error; }
}
