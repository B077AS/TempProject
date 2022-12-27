package DataBase;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
	private static final String PROPERTYDBDRIVER = "DBDRIVER";
	private static final String PROPERTYDBURL = "DBURL";
	private static final String PROPERTYNAME = "db_usn"; 
	private static final String PROPERTYPSW = "db_psw";

	private static String username;
	private static String password;
	private static String dbDriver;
	private static String dbURL;
	private static Connection conn;

	public static Connection connect() {

		try {
			Properties config= new Properties();
			FileInputStream fis;
			fis = new FileInputStream("Property/config.properties");
			config.load(fis);
			username=config.getProperty(PROPERTYNAME);
			password=config.getProperty(PROPERTYPSW);
			dbDriver =config.getProperty(PROPERTYDBDRIVER);
			dbURL =config.getProperty(PROPERTYDBURL);
		} catch (Exception e) {
			System.out.println("errore file properties");
			e.printStackTrace();
		}

		try {
			
			if (conn != null) {
				conn.close();
			}
			
			Class.forName(dbDriver);
			
			conn=DriverManager.getConnection(dbURL, username, password);

		} catch (Exception e1) {
			System.out.println("errore connessione");
			e1.printStackTrace();
		}
		
		return conn;
	}
}


