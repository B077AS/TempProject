package Users.Students;

import java.awt.*;
import javax.swing.*;
import Users.GeneralUser.*;

public class StudentsGUI extends UsersGUI{
	

	public StudentsGUI(String name, String lastName, String email, Students user) {
		super(name, lastName, email, user);

		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
		northPanel=head;
		addFindPanel(this, user);
		this.add(northPanel, BorderLayout.NORTH);
		setBackground(Color.WHITE);
	}
	
}

