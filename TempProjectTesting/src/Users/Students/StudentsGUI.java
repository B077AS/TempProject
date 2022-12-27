package Users.Students;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import Users.GeneralUser.UsersGUI;

public class StudentsGUI extends UsersGUI{
	
	
	public StudentsGUI(String name, String lastName, String email, Students user) {
		super(name, lastName, email, user);

		this.add(new StudentsMainPanel(name, lastName, email, this, user), BorderLayout.NORTH);
		
	}
	
	
}
