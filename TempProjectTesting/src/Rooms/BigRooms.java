package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Groups.Group;

public class BigRooms extends Rooms{


	private int availableSeats;
	private int maxOccupiedSeats;


	public BigRooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.maxOccupiedSeats=this.seats/2;
	}

	@Override
	public void book(Group group, String date, String startTime, String endTime) {
		String bookingID=date+"-"+startTime.split(":")[0]+"-"+endTime.split(":")[0]+"-"+this.code+"-"+group.getAdmin();

		try {
			Connection conn=DBConnection.connect();
			String query="insert into rooms_booking (Booking_ID, Date, Room, Group_ID, Start_Time, End_Time, Locked)"+"values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, bookingID);
			preparedStmt.setDate(2, Date.valueOf(date));
			preparedStmt.setString(3, this.code);
			preparedStmt.setString(4, group.getID());
			preparedStmt.setString(5, startTime);
			preparedStmt.setString(6, endTime);
			preparedStmt.setString(7, "false");

			preparedStmt.execute();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
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
			
			
			query="insert into solo_booking (Booking_ID, Date, Room, User_ID, Start_Time, End_Time)"+"values (?, ?, ?, ?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, bookingID);
			preparedStmt.setDate(2, Date.valueOf(date));
			preparedStmt.setString(3, this.code);
			preparedStmt.setString(4, user);
			preparedStmt.setString(5, startTime);
			preparedStmt.setString(6, endTime);

			preparedStmt.execute();
			conn.close();
			}
			else {
				throw new IllegalArgumentException();
			}

		} catch (SQLException e ) {

		}
	}

}
