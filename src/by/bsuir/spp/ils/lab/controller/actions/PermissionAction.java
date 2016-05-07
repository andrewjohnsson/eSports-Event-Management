package by.bsuir.spp.ils.lab.controller.actions;

import by.bsuir.spp.ils.lab.helper.PermissionHelper;
import by.bsuir.spp.ils.lab.service.AuthService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by andrewjohnsson on 06.05.16.
 */
public class PermissionAction extends ActionSupport {
	private AuthService service;
	private PermissionHelper helper;
	private boolean check;
	private String info;

	public PermissionAction(){
		setCheck(false);
		helper = new PermissionHelper();
		service = new AuthService();
	}

	public String isAdmin(){
		if (helper.UpdateUser() != null){
			if (helper.isAdmin()){
				setCheck(true);
			}else{
				setCheck(false);
				setInfo("You should be an admin to do it");
			}
		}else{
			setInfo("You are not logged in!");
		}
		return SUCCESS;
	}
	public String isViewer(){
		helper.UpdateUser();
		if (helper.canWatchGames()){
			setCheck(true);
		}else{
			setCheck(false);
		}
		return SUCCESS;
	}
	public String isPlayer(){
		helper.UpdateUser();
		if (helper.canPlayGames()){
			setCheck(true);
		}else{
			setCheck(false);
		}
		return SUCCESS;
	}
	public String isManager(){
		helper.UpdateUser();
		if (helper.canCreateTeam()){
			setCheck(true);
			return SUCCESS;
		}
		return ERROR;
	}
	public String isSupervisor(){
		helper.UpdateUser();
		if (helper.canAddEvent()){
			setCheck(true);
		}else{
			setCheck(false);
		}
		return SUCCESS;
	}

	public boolean getCheck(){return this.check;}
	public void setCheck(boolean value){ this.check = value; }
	public String getInfo() { return info; }
	public void setInfo(String info) { this.info = info; }
}
