package Rooms;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;

public class RoomDAO {


	public void insertRoom(Rooms room) {

		Connection conn=DBConnection.connect();

		try {
			String query="SELECT * FROM rooms WHERE Room_Code=?";
			PreparedStatement check=conn.prepareStatement(query);
			check.setString(1, room.getCode());

			ResultSet checkResult=check.executeQuery();

			if(checkResult.next()==false) {


				query="insert into rooms (Room_Code, Room_Type, Seats, LIM, Outlets, DisabledAccess)"+" values (?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, room.getCode());
				preparedStmt.setString(2, room.getType());
				preparedStmt.setInt(3, room.getSeats());
				preparedStmt.setString(4, Boolean.toString(room.isLIM()));
				preparedStmt.setString(5, Boolean.toString(room.isOutlets()));
				preparedStmt.setString(6, Boolean.toString(room.isDisabledAccess()));


				preparedStmt.execute();
				conn.close();
			}
			else {
				new ExceptionFrame("A Room with the same Code is already present!");
				conn.close();
				return;
			}
		} catch (SQLException e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	}

	public HashMap<String, Rooms> selectAllRooms() {
		try {
			HashMap<String, Rooms> allRooms=new HashMap<String, Rooms>();
			Properties config= new Properties();
			FileInputStream fis=new FileInputStream("Property/config.properties");
			config.load(fis);
			Connection conn=DBConnection.connect();

			String query="select * from rooms";
			Statement statement = conn.prepareStatement(query);
			ResultSet result=statement.executeQuery(query);

			while(result.next()) {
				String roomClassName=config.getProperty(result.getString(2));
				Rooms r=(Rooms)Class.forName(roomClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class).newInstance(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
				allRooms.put(result.getString(1), r);
			}
			return allRooms;
		} catch (Exception e) {
		}
		return null;		
	}

}
