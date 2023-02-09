package Users.LabManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataBase.DBConnection;
import Notifications.LabNotification;
import Rooms.BigRooms;
import Rooms.Booking;
import Users.GeneralUser.Users;

public class LabBookingDAO {
	
	public String selectID(LabNotification notification) throws Exception {
		Connection conn=DBConnection.connect();
		String query="select Booking_ID from lab_booking where Group_ID=? and Date=? and Start_Time=? and End_Time=? and Room=? and Reason=? and Locked='false'";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, notification.getGroupID());
		preparedStmt.setDate(2, notification.getDate());
		preparedStmt.setString(3, notification.getStartTime());
		preparedStmt.setString(4, notification.getEndTime());
		preparedStmt.setString(5, notification.getRoom());
		preparedStmt.setString(6, notification.getReason());
		ResultSet result=preparedStmt.executeQuery();							
		result.next();
		String bookingID=result.getString(1);
		conn.close();
		return bookingID;
	}
	
	public void delete(Booking booking) {
		try {
			Connection conn=DBConnection.connect();
			String query="delete from lab_booking where Booking_ID=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, booking.getBookingID());
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void update(Booking booking) {
		try {
			Connection conn=DBConnection.connect();
			String query="UPDATE lab_booking SET Locked='true' WHERE Booking_ID=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, booking.getBookingID());
			preparedStmt.executeUpdate();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public ArrayList<Booking> selectBookingsFromUser(Users user) {

		ArrayList<Booking> bookings=new ArrayList<Booking>();
		try {
			Connection conn=DBConnection.connect();
			String query="select * from (lab_booking) join (allgroups) on lab_booking.Group_ID=allgroups.Group_ID where allgroups.Partecipant=?";
			PreparedStatement preparedStmt=conn.prepareStatement(query);;
			preparedStmt.setString(1, user.getID());
			ResultSet result=preparedStmt.executeQuery();
			while(result.next()) {
				bookings.add(new Booking(result.getString(4), result.getString(5), result.getDate(3), new BigRooms(result.getString(6), null, "0", null, null, null), result.getString(1), result.getString(2), result.getString(8)));
			}
			return bookings;
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return bookings;		
	}
	
	
	public void deleteBooking(Booking booking){
		try {
			Connection conn=DBConnection.connect();
			String query="delete from lab_booking where Booking_ID=? and Date=? and Room=? and Group_ID=? and Start_Time=? and End_Time=? and Locked=?";
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
		catch(Exception e) {
		}
	}
	

}
