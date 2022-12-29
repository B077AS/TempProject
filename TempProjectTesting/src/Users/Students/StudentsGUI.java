package Users.Students;

import java.awt.*;
import javax.swing.*;
import Users.GeneralUser.*;

public class StudentsGUI extends UsersGUI{
	

	protected JPanel northPanel;
	public StudentsGUI(String name, String lastName, String email, Students user) {
		super(name, lastName, email, user);

		northPanel=new StudentsHeadPanel(name, lastName, email, user, this);
		this.add(northPanel, BorderLayout.NORTH);
	}
	
	
	public void removeHeadPanel(JPanel panel) {
		//this.removePanel();
		this.remove(this.northPanel);
		revalidate();
		repaint();
		this.northPanel=panel;
		add(this.northPanel, BorderLayout.NORTH);
		revalidate();
		repaint();
	}
	
}

