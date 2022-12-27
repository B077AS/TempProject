package MyLoader;

import java.io.*;
import java.sql.*;
import java.util.*;
import DataBase.DBConnection;
import Rooms.Rooms;

public class RoomLoader { //uso pattern factory
	
	private HashMap<String, Rooms> allRooms=new HashMap<String, Rooms>();
	
	public RoomLoader() throws Exception {
		Properties config= new Properties();
		FileInputStream fis=new FileInputStream("Property/config.properties");
		config.load(fis);
		Rooms r;
		try {
			Connection conn=DBConnection.connect();
			
			String query="select * from rooms";
			Statement statement = conn.prepareStatement(query);
			ResultSet result=statement.executeQuery(query);
			
			while(result.next()) {
				r=createRoom(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), config);
				allRooms.put(result.getString(1), r);
			}

		} catch (Exception e) {
			System.out.println("errore lettura da dbms");
		}		
	}
	
	public Rooms createRoom(String code, String type, String seats, String LIM, String outlets, String disabledAccess, Properties property) throws Exception{
		String roomClassName=property.getProperty(type);
		Rooms r=(Rooms)Class.forName(roomClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class).newInstance(code, type, seats, LIM, outlets, disabledAccess);
		return r;
	}
	
	public HashMap<String, Rooms> getRooms(){
		return this.allRooms;
	}

}
