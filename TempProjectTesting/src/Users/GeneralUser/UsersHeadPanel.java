package Users.GeneralUser;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import Users.Students.StudentsMainPanel;

public class UsersHeadPanel extends JPanel{
	
	public UsersHeadPanel(String name, String lastName, String email, Users user, UsersGUI gui) {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(user.getMainPanel(gui), BorderLayout.WEST);
		add(new NotificationRightPanel(user, gui), BorderLayout.EAST);
	}	
}