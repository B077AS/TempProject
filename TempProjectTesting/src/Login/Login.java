package Login;

import java.io.*;
import java.sql.*;
import java.util.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.GeneralUser.*;
import Users.Students.Students;

public class Login {

	public Login(String emailOrID, char[] password, LoginGUI frame) {
		
			String passwordString=new String(password);

			UsersDAO dao=new UsersDAO();
			dao.selectUser(new Students(null, null, null, emailOrID, passwordString, null));
			dao.getUser().GUI(frame);
	}
}
