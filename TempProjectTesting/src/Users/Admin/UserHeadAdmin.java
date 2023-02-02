package Users.Admin;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import Users.GeneralUser.NotificationRightPanel;
import Users.GeneralUser.UserGUI;
import Users.GeneralUser.Users;

public class UserHeadAdmin extends JPanel{
	public UserHeadAdmin(String name, String lastName, String email, Users user, UserGUI gui) {
		
		setLayout(new BorderLayout());
		setBackground(new Color(145,0,0));
		//setBackground(new Color(0,0,0,0));
		add(user.getMainPanel(gui), BorderLayout.WEST);
		
		
	}	

}
