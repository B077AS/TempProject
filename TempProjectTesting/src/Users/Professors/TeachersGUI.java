package Users.Professors;

import java.awt.*;
import javax.swing.*;
import Users.GeneralUser.UsersGUI;
import Users.Students.NotificationRightPanel;

public class TeachersGUI extends UsersGUI{
	
	private NotificationRightPanel nPanel;

	public TeachersGUI(String name, String lastName, String email, Teachers user) {
		super(name, lastName, email, user);
		
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(new TeachersMainPanel(name, lastName, email, this, user), BorderLayout.NORTH);
		
		//nPanel=new NotificationRightPanel(user, northPanel, this);
		//northPanel.add(nPanel, BorderLayout.EAST);


		this.add(northPanel, BorderLayout.NORTH);;

	}

}
