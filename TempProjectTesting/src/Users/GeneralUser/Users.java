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
	protected String type;
	protected List<Notification> notifications;
	//protected List<Notification> notifications; //questo non c'è
	
	public Users(String name, String lastName, String ID, String email, String password, String type) {
		this.name=name;
		this.lastName=lastName;
		this.ID=ID;
		this.email=email;
		this.password=password;
		this.notifications=new ArrayList();
		this.type= type;
		this.notifications=new ArrayList(); //non c'è
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
	
	public String getType() {
		return this.type;
	}
	
	//da qua in poi non c'è
	public abstract void GUI(LoginGUI frame);
	
	public abstract JButton checkNotifications();
	
	public abstract JPanel getMainPanel(UserGUI gui);
	
	public abstract JPanel book(Object[] objects, UserGUI frame);
	
	public abstract JPanel notificationPanel(Users user, UserGUI frame);
	
	public void loadNotifications(Notification notification) {
		this.notifications.add(notification);
	}
	
	public List<Notification> getNotifications(){
		return this.notifications;
	}
	
}
