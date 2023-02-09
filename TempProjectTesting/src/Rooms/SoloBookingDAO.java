package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.DBConnection;
import MyTimer.DateHolder;
import Users.GeneralUser.Users;

public class SoloBookingDAO {


	public boolean checkFromDateAndTime(Booking booking) {

		boolean check=false;
		
		Date mainDate=Date.valueOf(DateHolder.getYear()+"-"+DateHolder.getMonth()+"-"+DateHolder.getDay());

		try {
			Connection conn=DBConnection.connect();
			String query="select * from solo_booking where Date=? and Room=? and Start_Time=? and End_Time=?";
			PreparedStatement preparedStmt=conn.prepareStatement(query);;
			preparedStmt.setDate(1, mainDate);
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
	
	
	public ArrayList<Booking> selectBookingsFromUser(Users user) {
		
		ArrayList<Booking> bookings=new ArrayList<Booking>();
		try {
			Connection conn=DBConnection.connect();
			String query="select * from solo_booking where User_ID=?";
			PreparedStatement preparedStmt=conn.prepareStatement(query);;
			preparedStmt.setString(1, user.getID());
			ResultSet result=preparedStmt.executeQuery();
			
			while(result.next()) {
				bookings.add(new Booking(result.getString(5), result.getString(6), result.getDate(2), new BigRooms(result.getString(3), null, "0", null, null, null), result.getString(1), result.getString(4), result.getString(7)));
			}
			return bookings;

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return bookings;		
	}
	
	public void deleteBooking(Booking booking) throws Exception {
			Connection conn=DBConnection.connect();
			String query="delete from solo_booking where Booking_ID=? and Date=? and Room=? and User_ID=? and Start_Time=? and End_Time=? and Locked=?";
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
