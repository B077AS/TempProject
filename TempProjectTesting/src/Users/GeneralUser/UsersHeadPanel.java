package Users.GeneralUser;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Users.Students.StudentsMainPanel;

public class UsersHeadPanel extends JPanel{
	
	public UsersHeadPanel(String name, String lastName, String email, Users user, UserGUI gui) {
		setLayout(new BorderLayout());
		setBackground(new Color(145,0,0));
		//setBackground(new Color(0,0,0,0));
		add(user.getMainPanel(gui), BorderLayout.WEST);
		add(new NotificationRightPanel(user, gui), BorderLayout.EAST);
		
		
		
	}	
}