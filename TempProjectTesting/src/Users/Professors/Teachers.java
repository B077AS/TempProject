package Users.Professors;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Login.*;
import Notifications.JoinGroupNotification;
import Rooms.Booking;
import Rooms.Rooms;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import Users.Students.StudentsMainPanel;

public class Teachers extends Users{

	public Teachers(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		TeachersGUI gui=new TeachersGUI(this.name, this.lastName, this.email, this);
		frame.dispose();
		
	}

	@Override
	public JButton checkNotifications() {
		ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-active.png");
		Image image = notificationIcon.getImage();
		Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
		notificationIcon = new ImageIcon(newimg);
		return new JButton(notificationIcon);
	}

	@Override
	public void loadNotifications(JoinGroupNotification notification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getMainPanel(UsersGUI gui) {
		// TODO Auto-generated method stub
		return new TeachersMainPanel(name, lastName, email, gui, this);
	}

	@Override
	public JPanel book(Object[] objects, UsersGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}
}
