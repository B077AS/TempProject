package Users.GeneralUser;


import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import Login.LoginGUI;
import Notifications.*;

public abstract class Users {
	
	protected String name;
	protected String lastName;
	protected String ID;
	protected String email;
	protected String password;
	protected List<Notification> notifications;
	
	public Users(String name, String lastName, String ID, String email, String password) {
		this.name=name;
		this.lastName=lastName;
		this.ID=ID;
		this.email=email;
		this.password=password;
		this.notifications=new ArrayList();
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getID() {
		return this.ID;
	}
	
	
	public abstract void GUI(LoginGUI frame);
	
	public abstract JButton checkNotifications();
	
	public abstract JPanel getMainPanel(UsersGUI gui);
	
	public abstract JPanel book(Object[] objects, UsersGUI frame);
	
	public abstract JPanel notificationPanel(Users user, UsersGUI frame);
	
	public void loadNotifications(Notification notification) {
		this.notifications.add(notification);
	}
	
	public List<Notification> getNotifications(){
		return this.notifications;
	}
	
}
