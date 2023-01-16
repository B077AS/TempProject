package Users.GeneralUser;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;

public class UsersDAO {
	
	private Users user;
	
	public void insertUser(Users user) {
		
	}
	
	public void selectUser(Users user) {
		int ID=0;
		String type="";
		String name="";
		String lastName="";
		String email="";
		try {

			Connection conn=DBConnection.connect();

			String query="select * from users where User_Code=? and Password=? or Email=? and Password=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, user.getEmail());
			preparedStmt.setString(2, user.getPassword());
			preparedStmt.setString(3, user.getEmail());
			preparedStmt.setString(4, user.getPassword());

			ResultSet result=preparedStmt.executeQuery();

			result.next();
			ID=result.getInt(1);
			type=result.getString(2);
			name=result.getString(3);
			lastName=result.getString(4);
			email=result.getString(5);

		} catch (Exception e1) {
			new ExceptionFrame("\u274C Wrong Email or Password");
			return;
		}
		
		try {
			Properties config= new Properties();
			FileInputStream fis=new FileInputStream("Property/config.properties");
			config.load(fis);
			String userClassName=config.getProperty(type);
			Users u=(Users)Class.forName(userClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class).newInstance(name, lastName, Integer.toString(ID), email, user.getPassword());
			this.user=u;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("errore lettura properties");
		}

	}

	public Users getUser() {
		return user;
	}
	
	

}
