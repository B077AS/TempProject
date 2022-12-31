package Users.Admin;

import javax.swing.JButton;
import javax.swing.JPanel;

import Login.*;
import Notifications.JoinGroupNotification;
import Rooms.Booking;
import Rooms.Rooms;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;

public class Administrator extends Users {


	public Administrator(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		AdminGUI gui=new AdminGUI(this.name, this.lastName, this.email, this);
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
