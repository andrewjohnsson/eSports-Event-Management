package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.entity.Ticket;
import by.bsuir.spp.ils.lab.entity.User;
import by.bsuir.spp.ils.lab.service.AuthService;
import by.bsuir.spp.ils.lab.service.TicketService;
import by.bsuir.spp.ils.lab.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by andrewjohnsson on 11.04.16.
 */
public class AuthAction extends ActionSupport {
  private AuthService authService;
	private UserService userService;
	private TicketService ticketService;
  private User user;
	private List<Ticket> tickets;
	private String error;

  public AuthAction(){
		authService = new AuthService();
		userService = new UserService();
		ticketService = new TicketService();
		setError(null);
  }

  public String login(){
    if (this.getUser() != null) {
      if (getUser().getEmail().length() < 4 || getUser().getPassword().length() < 4) {
        System.out.println("Incorrect login or pass length");
        return ERROR;
      } else {
				try {
					setUser(authService.check(getUser().getEmail(), getUser().getPassword()));
					setTickets(ticketService.findUserTickets(getUser().getId()));
					authService.setUserLogin(getUser().getId());
				}catch (Exception e){
					setUser(null);
					setTickets(null);
					setError("Cannot login!");
				}
      }
    }
    return SUCCESS;
  }

	public String register(){
		if ((getUser() != null) && authService.getUserId() == 0) {
			try {
				setUser(userService.add(getUser()));
				setTickets(ticketService.findUserTickets(getUser().getId()));
				return SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String check(){
		int id = authService.getUserId();
		if (id > 0 ){
			setUser(userService.find(id));
			setTickets(ticketService.findUserTickets(id));
		}else {
			setUser(null);
			setTickets(null);
			setError("You are not logged in!");
		}
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
	public List<Ticket> getTickets(){ return tickets; }
	public void setTickets(List<Ticket> tickets){ this.tickets = tickets; }
	public String getError() { return error; }
	public void setError(String error) { this.error = error; }
}
