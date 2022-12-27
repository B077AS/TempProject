package Users.LabManager;

import Login.*;
import Users.GeneralUser.Users;

public class Lab_Manager extends Users{



	public Lab_Manager(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		// TODO Auto-generated method stub
		frame.dispose();
	}


}
