package Users.Students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import javax.management.Notification;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Login.*;
import Notifications.JoinGroupNotification;
import Users.GeneralUser.Users;
import Groups.Group;


public class Students extends Users{
	
	private HashMap<String, Group> groups;
	List<JoinGroupNotification>  notifications;

	public Students(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		this.groups=new HashMap<String, Group>();
		this.notifications=new ArrayList<JoinGroupNotification>();
	}

	@Override
	public void GUI(LoginGUI frame) {
		StudentsGUI gui=new StudentsGUI(name, lastName, email, this);
		frame.dispose();
	}	

	public void createGroup(String groupName){
		try {

			Connection conn=DBConnection.connect();
			
			String query="insert into allgroups (Group_ID, Admin, Partecipant)"+"values (?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, groupName);
			preparedStmt.setString(2, this.ID);
			preparedStmt.setString(3, this.ID);
			
			preparedStmt.execute();
			conn.close();
			
			
		} catch (Exception e) {
			new ExceptionFrame("\u274C Group Name not Valid!");
			return;
		}	
	}
	
	
	public void loadNotifications(JoinGroupNotification notification) {
		this.notifications.add(notification);
	}
	
	public List<JoinGroupNotification> getNotifications(){
		return this.notifications;
	}

	public HashMap<String, Group> getGroups(){
		return this.groups;
	}
	
}
