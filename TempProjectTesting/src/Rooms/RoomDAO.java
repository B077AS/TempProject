package Rooms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
