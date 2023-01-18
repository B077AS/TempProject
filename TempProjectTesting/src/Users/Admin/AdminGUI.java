package Users.Admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;

public class AdminGUI extends UserGUI{

	public AdminGUI(String name, String lastName, String email, Users user) {
		super(name, lastName, email, user);
		
		this.add(new AdminMainPanel(name, lastName, email, this), BorderLayout.NORTH);
	}

}
