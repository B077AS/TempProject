package Users.LabManager;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import Users.GeneralUser.Users;
import Users.GeneralUser.UserGUI;
import Users.GeneralUser.UsersHeadPanel;

public class LabManagerGUI extends UserGUI{

	public LabManagerGUI(String name, String lastName, String email, Users user) {
		super(name, lastName, email, user);
		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
		northPanel=head;
		this.add(northPanel, BorderLayout.NORTH);
		this.add(downPanel(), BorderLayout.SOUTH);
		

	}
	
	@Override
	public void addFindPanel(UserGUI gui, Users user) {
		
	}
}
