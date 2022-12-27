package Users.Professors;

import Login.*;
import Users.GeneralUser.Users;

public class Teachers extends Users{

	public Teachers(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		frame.dispose();
		
	}


}
