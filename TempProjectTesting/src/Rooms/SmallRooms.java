package Rooms;

import java.sql.Date;
import Exceptions.ExceptionFrame;
import Groups.Group;

public class SmallRooms extends Rooms{

	private final int minimumGroupSize=3;

	public SmallRooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.soloBookable=false;
	}


	@Override
	public void book(Group group, String date, String startTime, String endTime) {
		
		try {
			if(group.getStudentsNumber()<minimumGroupSize) {
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException e) {
			new ExceptionFrame("Required at least 3 Students to book this Room!");
			return;
		}			
		
		String bookingID=date+"-"+startTime.split(":")[0]+"-"+endTime.split(":")[0]+"-"+this.code+"-"+group.getGroupAdmin();		

		RoomsBookingDAO bookingDAO=new RoomsBookingDAO();
		bookingDAO.insert(new Booking(startTime, endTime, Date.valueOf(date), this, bookingID, group.getGroupID(), "true"));
		
		new BookingSuccessful();

	}


}
