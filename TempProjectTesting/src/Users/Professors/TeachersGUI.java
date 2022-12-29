package Users.Professors;

import java.awt.BorderLayout;

import Users.GeneralUser.UsersGUI;

public class TeachersGUI extends UsersGUI{

	public TeachersGUI(String name, String lastName, String email, Teachers user) {
		super(name, lastName, email, user);

		this.add(new TeachersMainPanel(name, lastName, email, this, user), BorderLayout.NORTH);
	}

}
