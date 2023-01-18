package Users.Professors;

import java.awt.*;
import javax.swing.*;

import Users.GeneralUser.NotificationRightPanel;
import Users.GeneralUser.UserGUI;
import Users.GeneralUser.UsersHeadPanel;

public class ProfessorGUI extends UserGUI{
	
	private boolean skip;

	public ProfessorGUI(String name, String lastName, String email, Professor user) {
		super(name, lastName, email, user);
		
		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
		northPanel=head;
		addFindPanel(this, user);
		this.add(northPanel, BorderLayout.NORTH);

	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	

}
