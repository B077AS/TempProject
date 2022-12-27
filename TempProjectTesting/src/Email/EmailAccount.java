package Email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import DataBase.DBConnection;
import Exceptions.LoginException;

public class EmailAccount {
	
	private String email;
	private String password;
	
	public EmailAccount() throws FileNotFoundException, IOException {
		
		try {

			Connection conn=DBConnection.connect();

			String query="select * from email";
			Statement statement = conn.prepareStatement(query);
			ResultSet result=statement.executeQuery(query);
			result.next();
			
			this.email=result.getString(1);
			this.password=result.getString(2);
			
			conn.close();
			
		} catch (Exception e1) {
			new LoginException();
			return;
		}

	}

	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}


}