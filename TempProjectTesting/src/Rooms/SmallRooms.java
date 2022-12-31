package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Groups.Group;
import Users.GeneralUser.UsersGUI;

public class SmallRooms extends Rooms{



	public SmallRooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.soloBookable=false;
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
			preparedStmt.setString(7, "true");

			preparedStmt.execute();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}


}
