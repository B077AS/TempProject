package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Groups.Group;
import Users.GeneralUser.UserGUI;

public class SmallRooms extends Rooms{



	public SmallRooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.soloBookable=false;
	}


	@Override
	public void book(Group group, String date, String startTime, String endTime) {
		String bookingID=date+"-"+startTime.split(":")[0]+"-"+endTime.split(":")[0]+"-"+this.code+"-"+group.getGroupAdmin();

		RoomsBookingDAO bookingDAO=new RoomsBookingDAO();
		bookingDAO.insert(new Booking(startTime, endTime, Date.valueOf(date), this, bookingID, group.getGroupID(), "true"));
		
		new BookingSuccessful();

	}


}
