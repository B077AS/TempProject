package Users.GeneralUser;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Users.Students.Students;

public class UsersDAO {
	
	private Users user;
	
	public void insertUser(Users user) {
		
		
		Connection conn=DBConnection.connect();
		try {
			String query="insert into users (User_Code, User_Type, Name, Last_Name, Email, Password)"+"values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, user.ID);
			preparedStmt.setString(2, user.type.toString());
			preparedStmt.setString(3, user.name);
			preparedStmt.setString(4, user.lastName);
			preparedStmt.setString(5, user.email);
			preparedStmt.setString(6, user.password);
			
			preparedStmt.execute();
			conn.close();


		} catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
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
			Users u=(Users)Class.forName(userClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class).newInstance(name, lastName, Integer.toString(ID), email, user.getPassword(), type);
			this.user=u;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("errore lettura properties");
		}

	}

	public Users getUser() {
		return user;
	}
	
	public Users checkUser(Users user) throws Exception {
			Connection conn=DBConnection.connect();
			String query="select User_Code, Email from users where User_Code=? or Email=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(user.getEmail()));
			preparedStmt.setString(2, user.getEmail());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			return new Students(null, null, result.getString(1), result.getString(1), null, null);
	}
	
	

}
