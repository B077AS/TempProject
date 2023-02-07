package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Groups.Group;
import Groups.GroupDAO;
import MyTimer.DateHolder;

public class BigRooms extends Rooms{

	public BigRooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
	}

	@Override
	public void book(Group group, Booking booking) {
		String bookingID=booking.getDate().toString()+"-"+booking.getStartTime().split(":")[0]+"-"+booking.getEndTime().split(":")[0]+"-"+this.code+"-"+group.getGroupAdmin();

		GroupDAO daoGroup=new GroupDAO();
		RoomsBookingDAO bookingDAO=new RoomsBookingDAO();


		try {
			
			int newPartecipants=daoGroup.countPartecipant(group);
			Connection conn=DBConnection.connect();
			String query="select\r\n"
					+ "(select count(*) from allgroups where Group_ID in (select Group_ID from rooms_booking where rooms_booking.Date=? and Room=? and Start_Time=? and End_Time=?))\r\n"
					+ "+(select count(*) from solo_booking where solo_booking.Date=? and Room=? and Start_Time=? and End_Time=?) as sum";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setDate(1, Date.valueOf(DateHolder.getYear()+"-"+DateHolder.getMonth()+"-"+DateHolder.getDay()));
			preparedStmt.setString(2, this.code);
			preparedStmt.setString(3, booking.getStartTime());
			preparedStmt.setString(4, booking.getEndTime());
			preparedStmt.setDate(5, Date.valueOf(DateHolder.getYear()+"-"+DateHolder.getMonth()+"-"+DateHolder.getDay()));
			preparedStmt.setString(6, this.code);
			preparedStmt.setString(7, booking.getStartTime());
			preparedStmt.setString(8, booking.getEndTime());
			ResultSet result=preparedStmt.executeQuery();
			result.next();

			int alreadyBooked=result.getInt(1);

			if((newPartecipants+alreadyBooked)>this.maxOccupiedSeats) {
				throw new IllegalArgumentException();
			}

			bookingDAO.insert(new Booking(booking.getStartTime(), booking.getEndTime(), booking.getDate(), this, bookingID, group.getGroupID(), "false"));
			conn.close();
			new BookingSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
			new ExceptionFrame("Not enough Seats!");
			return;
		}

	}

	@Override
	public void soloBook(String user, Booking booking) throws IllegalArgumentException{
		String bookingID=booking.getDate()+"-"+booking.getStartTime().split(":")[0]+"-"+booking.getEndTime().split(":")[0]+"-"+this.code+"-"+user;
		SoloBookingDAO soloDAO=new SoloBookingDAO();
		try {
			Connection conn=DBConnection.connect();


			String query ="select Partecipant from allgroups where Group_ID in(select Group_ID from rooms_booking where Date=? and Room=? and Start_Time=? and End_Time=?) and Partecipant=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setDate(1, booking.getDate());
			preparedStmt.setString(2, this.code);
			preparedStmt.setString(3, booking.getStartTime());
			preparedStmt.setString(4, booking.getEndTime());
			preparedStmt.setString(5, user);
			ResultSet result=preparedStmt.executeQuery();

			if(result.next()==false) {
				soloDAO.insert(new Booking(booking.getStartTime(), booking.getEndTime(), booking.getDate(), this, bookingID, user, "false"));
				new BookingSuccessful();
			}
			else {
				throw new IllegalArgumentException();
			}

		} catch (Exception e ) {
			new ExceptionFrame("Already Booked!");
		}
	}

}
