package Login;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import Users.GeneralUser.UsersDAO;
import Users.Students.Students;


public class testLogin {
	
	LoginGUI log;
	
	@Test
	public void start(){
		
		log= new LoginGUI();
	}

	Login l;
	String emailOrID = "jobanpreet.kaur01@universitadipavia.it";
	String passwordString= "password";
	
	@Test

		public void login() {
			UsersDAO dao=new UsersDAO();
			dao.selectUser(new Students(null, null, null, emailOrID, passwordString, null));
			dao.getUser().GUI(log);
		}
	
	@Test
	public void fieldCheck() throws IllegalArgumentException{
		if(emailOrID.isEmpty()==true || passwordString.isEmpty()==true) {
			throw new IllegalArgumentException();
		}
	}
		
	

}
