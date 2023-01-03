package Users.LabManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import Login.*;
import Notifications.Notification;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import Users.Professors.TeachersGUI;
import Users.Professors.TeachersMainPanel;

public class Lab_Manager extends Users{



	public Lab_Manager(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		LabManagerGUI gui=new LabManagerGUI(this.name, this.lastName, this.email, this);
		frame.dispose();
	}

	@Override
	public JButton checkNotifications() {
		// TODO Auto-generated method stub
		return new JButton("notifiche");
	}

	@Override
	public JPanel getMainPanel(UsersGUI gui) {
		
		return new LabManagerMainPanel(name, lastName, email, gui, this);
	}

	@Override
	public JPanel book(Object[] objects, UsersGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel notificationPanel(Users user, UsersGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}
}
