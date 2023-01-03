package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Groups.Group;
import MyTimer.DateHolder;

public class BigRooms extends Rooms{


	private int availableSeats;


	public BigRooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
	}

	@Override
	public void book(Group group, String date, String startTime, String endTime) {
		String bookingID=date+"-"+startTime.split(":")[0]+"-"+endTime.split(":")[0]+"-"+this.code+"-"+group.getAdmin();

		try {
			Connection conn=DBConnection.connect();
			
			String query ="select count(Partecipant) from allgroups where Group_ID=? and Admin=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, group.getID());
			preparedStmt.setString(2, group.getAdmin());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			
			int newPartecipants=result.getInt(1);
			
			query="select\r\n"
					+ "(select count(*) from allgroups where Group_ID in (select Group_ID from rooms_booking where rooms_booking.Date=? and Room=? and Start_Time=? and End_Time=?))\r\n"
					+ "+(select count(*) from solo_booking where solo_booking.Date=? and Room=? and Start_Time=? and End_Time=?) as sum";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setDate(1, Date.valueOf(DateHolder.getYear()+"-"+DateHolder.getMonth()+"-"+DateHolder.getDay()));
			preparedStmt.setString(2, this.code);
			preparedStmt.setString(3, startTime);
			preparedStmt.setString(4, endTime);
			preparedStmt.setDate(5, Date.valueOf(DateHolder.getYear()+"-"+DateHolder.getMonth()+"-"+DateHolder.getDay()));
			preparedStmt.setString(6, this.code);
			preparedStmt.setString(7, startTime);
			preparedStmt.setString(8, endTime);
			result=preparedStmt.executeQuery();
			result.next();
			
			int alreadyBooked=result.getInt(1);
			
			if((newPartecipants+alreadyBooked)>this.maxOccupiedSeats) {
				throw new IllegalArgumentException();
			}
			
			query="insert into rooms_booking (Booking_ID, Date, Room, Group_ID, Start_Time, End_Time, Locked)"+"values (?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, bookingID);
			preparedStmt.setDate(2, Date.valueOf(date));
			preparedStmt.setString(3, this.code);
			preparedStmt.setString(4, group.getID());
			preparedStmt.setString(5, startTime);
			preparedStmt.setString(6, endTime);
			preparedStmt.setString(7, "false");

			preparedStmt.execute();
			conn.close();
			new BookingSuccessful();
		} catch (Exception e) {
			new ExceptionFrame("Not enough Seats!");
			return;
		}

	}

	@Override
	public void soloBook(String user, String date, String startTime, String endTime) throws IllegalArgumentException{
		String bookingID=date+"-"+startTime.split(":")[0]+"-"+endTime.split(":")[0]+"-"+this.code+"-"+user;

		try {
			Connection conn=DBConnection.connect();
			 
			
			String query ="select Partecipant from allgroups where Group_ID in(select Group_ID from rooms_booking where Date=? and Room=? and Start_Time=? and End_Time=?) and Partecipant=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setDate(1, Date.valueOf(date));
			preparedStmt.setString(2, this.code);
			preparedStmt.setString(3, startTime);
			preparedStmt.setString(4, endTime);
			preparedStmt.setString(5, user);
			ResultSet result=preparedStmt.executeQuery();
			
			if(result.next()==false) {
			
			
			query="insert into solo_booking (Booking_ID, Date, Room, User_ID, Start_Time, End_Time, Locked)"+"values (?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, bookingID);
			preparedStmt.setDate(2, Date.valueOf(date));
			preparedStmt.setString(3, this.code);
			preparedStmt.setString(4, user);
			preparedStmt.setString(5, startTime);
			preparedStmt.setString(6, endTime);
			preparedStmt.setString(7, "false");
			preparedStmt.execute();
			conn.close();
			new BookingSuccessful();
			}
			else {
				throw new IllegalArgumentException();
			}

		} catch (SQLException e ) {

		}
	}

}
