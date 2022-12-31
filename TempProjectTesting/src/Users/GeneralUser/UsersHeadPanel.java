package Users.GeneralUser;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Users.Students.NotificationRightPanel;
import Users.Students.StudentsMainPanel;

public class UsersHeadPanel extends JPanel{
	
	public UsersHeadPanel(String name, String lastName, String email, Users user, UsersGUI gui) {
		setLayout(new BorderLayout());
		add(user.getMainPanel(gui), BorderLayout.NORTH);
		add(new FindPanel(gui, user), BorderLayout.CENTER);
		add(new NotificationRightPanel(user, gui), BorderLayout.EAST);
	}	
}
