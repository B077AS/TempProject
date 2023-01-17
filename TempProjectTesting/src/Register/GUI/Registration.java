package Register.GUI;

import java.io.*;
import java.sql.*;
import DataBase.DBConnection;
import ENUM.*;
import Users.GeneralUser.UsersDAO;
import Users.Students.Students;

public class Registration {



	public Registration(String name, String lastName, String idNumber,String email, char[] password, UserType type) {
		String passwordString=new String(password);//converto la password in una stringa
		try {
			UsersDAO dao=new UsersDAO();
			
			dao.insertUser(new Students(name,lastName,idNumber,email,passwordString,type.toString()));
		} catch (Exception e) {
			System.out.println("file user non letto");
		}
	}

	

}

