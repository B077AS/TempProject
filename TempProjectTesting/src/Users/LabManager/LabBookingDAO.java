package Users.LabManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;
import Notifications.LabNotification;
import Rooms.Booking;

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
	

}
