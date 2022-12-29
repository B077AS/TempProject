package Users.Students;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import Users.GeneralUser.FindPanel;
import Users.GeneralUser.UsersGUI;

public class StudentsGUI extends UsersGUI{
	
	
	public StudentsGUI(String name, String lastName, String email, Students user) {
		super(name, lastName, email, user);
		
		JPanel northPanel=new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(new StudentsMainPanel(name, lastName, email, this, user), BorderLayout.NORTH);
		northPanel.add(new FindPanel(this, user), BorderLayout.CENTER);
		
		this.add(northPanel, BorderLayout.NORTH);

	}
	
	
}
