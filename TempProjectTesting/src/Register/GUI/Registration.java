package Register.GUI;

import java.io.*;
import java.sql.*;
import DataBase.DBConnection;
import ENUM.*;

public class Registration {


	public Registration(String name, String lastName, String idNumber,String email, char[] password, UserType type) {
		String passwordString=new String(password);//converto la password in una stringa
		try {
			register(name, lastName, idNumber,email, passwordString, type);
		} catch (IOException e) {
			System.out.println("file user non letto");
		}
	}

	public void register(String name, String lastName, String idNumber,String email, String password, UserType type) throws IOException {
		Connection conn=DBConnection.connect();
		try {
			String query="insert into users (User_Code, User_Type, Name, Last_Name, Email, Password)"+"values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, idNumber);
			preparedStmt.setString(2, type.toString());
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, lastName);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, password);
			
			preparedStmt.execute();
			conn.close();


		} catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	}


}

