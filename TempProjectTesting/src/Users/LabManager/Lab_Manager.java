package Users.LabManager;

import javax.swing.JButton;
import javax.swing.JPanel;

import Login.*;
import Notifications.JoinGroupNotification;
import Rooms.Booking;
import Rooms.Rooms;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;

public class Lab_Manager extends Users{



	public Lab_Manager(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		// TODO Auto-generated method stub
		frame.dispose();
	}

	@Override
	public JButton checkNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadNotifications(JoinGroupNotification notification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getMainPanel(UsersGUI gui) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel book(Object[] objects, UsersGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}
}
