package Users.Students;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.management.Notification;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Login.*;
import MyLoader.RoomLoader;
import Notifications.JoinGroupNotification;
import Rooms.*;
import Users.GeneralUser.*;
import Groups.Group;
import Groups.GroupsPanel;


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

	public HashMap<String, Group> getGroups(){
		return this.groups;
	}
	
	public JButton checkNotifications() {

		try {

			Connection conn=DBConnection.connect();
			String query="select * from group_notifications where Receiver=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(this.getID()));
			ResultSet result=preparedStmt.executeQuery();

			if (result.next() == true) {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-active.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}
			else {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-inactive.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}


		} catch (Exception e) {
		}
		return null;	

	}

	@Override
	public JPanel getMainPanel(UsersGUI gui) {
		return new StudentsMainPanel(name, lastName, email, gui, this);
	}
	
	
	public JPanel book(Object[] objects, UsersGUI frame) {
		int year=DateHolder.getYear();
		int month=DateHolder.getMonth();
		int day=DateHolder.getDay();
		
		JPanel bookPanel=new JPanel();
		
		bookPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		GroupsPanel groupsPanel=new GroupsPanel(this, frame);
		HashMap<String, Group> groups=groupsPanel.getUserSpecificGroups();
		ArrayList<Group> groupsArray=new ArrayList<Group>();
		for(HashMap.Entry<String, Group> group : groups.entrySet()) {
			groupsArray.add(group.getValue());
		}
		JLabel selectGroup=new JLabel("Select Group: ");
		c.gridx=0;
		c.gridy=0;
		bookPanel.add(selectGroup, c);
		JComboBox<Group> goupsBox=new JComboBox(groupsArray.toArray());
		c.gridx=1;
		c.gridy=0;
		bookPanel.add(goupsBox, c);
		JButton groupButton=new JButton("Book as a Group");
		groupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Booking booking=(Booking)objects[0];
				try {
					RoomLoader rooms=new RoomLoader();
					rooms.getRooms().get(booking.getRoom().getCode());
					Group myGroup=(Group)goupsBox.getSelectedItem();
					booking.getRoom().book(myGroup, year+"-"+month+"-"+day, booking.getStartTime(), booking.getEndTime());
					new BookingSuccessful();
					frame.removePanel();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		
			}
			
		});
		c.gridx=2;
		c.gridy=0;
		bookPanel.add(groupButton, c);
		JButton soloButton=new JButton("Book as an Individual");
		soloButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Booking booking=(Booking)objects[0];
				try {
					RoomLoader rooms=new RoomLoader();
					rooms.getRooms().get(booking.getRoom().getCode());
					try {
					booking.getRoom().soloBook(ID, year+"-"+month+"-"+day, booking.getStartTime(), booking.getEndTime());
					}catch(Exception ex) {
						new ExceptionFrame("Not Allowed to Register as and Individual");
						return;
					}
					new BookingSuccessful();
					frame.removePanel();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		
			}
			
		});
		c.gridx=2;
		c.gridy=1;
		bookPanel.add(soloButton, c);
		return bookPanel;
		
	}
}
