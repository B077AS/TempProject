package Users.LabManager;

import java.awt.BorderLayout;

import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;
import Users.GeneralUser.UsersHeadPanel;

public class LabManagerGUI extends UsersGUI{

	public LabManagerGUI(String name, String lastName, String email, Users user) {
		super(name, lastName, email, user);
		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
		northPanel=head;
		this.add(northPanel, BorderLayout.NORTH);
	}
	
	@Override
	public void addFindPanel(UsersGUI gui, Users user) {
		
	}
}
