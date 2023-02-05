package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;

public class SoloBookingDAO {


	public boolean checkFromDateAndTime(Booking booking) {

		boolean check=false;

		try {
			Connection conn=DBConnection.connect();
			String query="select * from solo_booking where Date=? and Room=? and Start_Time=? and End_Time=?";
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
	
	public void insert(Booking booking) throws Exception {
		Connection conn=DBConnection.connect();
		String query="insert into solo_booking (Booking_ID, Date, Room, User_ID, Start_Time, End_Time, Locked)"+"values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1, booking.getBookingID());
		preparedStmt.setDate(2, booking.getDate());
		preparedStmt.setString(3, booking.getRoom().getCode());
		preparedStmt.setString(4, booking.getPeopleID());
		preparedStmt.setString(5, booking.getStartTime());
		preparedStmt.setString(6, booking.getEndTime());
		preparedStmt.setString(7, Boolean.toString(booking.isLocked()));
		preparedStmt.execute();
		conn.close();
	}
}
