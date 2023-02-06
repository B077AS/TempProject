package Login;

import java.io.*;
import java.sql.*;
import java.util.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.*;
import Users.Students.Students;

public class Login {
	String emailOrID;
	String passwordString;
	LoginGUI frame;

	public Login(String emailOrID, char[] password, LoginGUI frame) {
			this.emailOrID=emailOrID;
			this.passwordString=new String(password);
			this.frame=frame;

	}	
	
	public void login() {
		UsersDAO dao=new UsersDAO();
		dao.selectUser(new Students(null, null, null, emailOrID, passwordString, null));
		dao.getUser().GUI(frame);
	}
	
	
	public void fieldCheck() throws IllegalArgumentException{
		if(emailOrID.isEmpty()==true || passwordString.isEmpty()==true) {
			throw new IllegalArgumentException();
		}
	}
	
}
