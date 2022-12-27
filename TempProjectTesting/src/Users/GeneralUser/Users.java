package Users.GeneralUser;

import Login.LoginGUI;

public abstract class Users {
	
	protected String name;
	protected String lastName;
	protected String ID;
	protected String email;
	protected String password;
	
	public Users(String name, String lastName, String ID, String email, String password) {
		this.name=name;
		this.lastName=lastName;
		this.ID=ID;
		this.email=email;
		this.password=password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	
	
	public abstract void GUI(LoginGUI frame);
	
	
}
