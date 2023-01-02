package Users.Professors;

import java.awt.*;
import javax.swing.*;

import Users.GeneralUser.NotificationRightPanel;
import Users.GeneralUser.UsersGUI;
import Users.GeneralUser.UsersHeadPanel;

public class TeachersGUI extends UsersGUI{
	
	private boolean skip;

	public TeachersGUI(String name, String lastName, String email, Teachers user) {
		super(name, lastName, email, user);
		
		northPanel=new UsersHeadPanel(name, lastName, email, user, this);
		this.add(northPanel, BorderLayout.NORTH);

	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	

}
