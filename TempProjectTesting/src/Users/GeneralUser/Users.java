package Users.GeneralUser;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import DataBase.DBConnection;
import Login.LoginGUI;
import Notifications.JoinGroupNotification;
import Rooms.Booking;
import Rooms.Rooms;
import Users.Students.Students;

public abstract class Users {
	
	protected String name;
	protected String lastName;
	protected String ID;
	protected String email;
	protected String password;
	protected List notifications;
	
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
	
	public abstract void loadNotifications(JoinGroupNotification notification);
	
	public List getNotifications(){
		return this.notifications;
	}
	
	public abstract JPanel getMainPanel(UsersGUI gui);
	
	public abstract JPanel book(Object[] objects, UsersGUI frame);
	
}
