package Users.Students;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Users.GeneralUser.FindPanel;

public class StudentsHeadPanel extends JPanel{
	
	public StudentsHeadPanel(String name, String lastName, String email, Students user, StudentsGUI gui) {
		setLayout(new BorderLayout());
		add(new StudentsMainPanel(name, lastName, email, gui, user), BorderLayout.NORTH);
		add(new FindPanel(gui, user), BorderLayout.CENTER);
		add(new NotificationRightPanel(user, gui), BorderLayout.EAST);
	}	
}
