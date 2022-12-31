package Users.Professors;

import java.awt.*;
import javax.swing.*;
import Users.GeneralUser.UsersGUI;
import Users.GeneralUser.UsersHeadPanel;
import Users.Students.NotificationRightPanel;

public class TeachersGUI extends UsersGUI{

	public TeachersGUI(String name, String lastName, String email, Teachers user) {
		super(name, lastName, email, user);
		
		northPanel=new UsersHeadPanel(name, lastName, email, user, this);
		this.add(northPanel, BorderLayout.NORTH);

	}

}
