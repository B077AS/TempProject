package Rooms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

public class RoomsBookingDAO {

	
	public boolean checkFromDateAndTime(Booking booking) {

		boolean check=false;

		try {
			Connection conn=DBConnection.connect();
			String query="select * from rooms_booking where Date=? and Room=? and Start_Time=? and End_Time=?";
			PreparedStatement preparedStmt=conn.prepareStatement(query);;
			preparedStmt.setDate(1, booking.getDate());
			preparedStmt.setString(2, booking.getRoom().getCode());
			preparedStmt.setString(3, booking.getStartTime());
			preparedStmt.setString(4, booking.getEndTime());
			ResultSet result=preparedStmt.executeQuery();

			check=result.next();
			return check;
		}

		catch (Exception ex) {
		}
		return check;

	}
}
