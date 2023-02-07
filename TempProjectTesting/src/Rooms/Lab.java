package Rooms;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JPanel;

import Exceptions.ExceptionFrame;
import Groups.Group;
import Notifications.LabNotification;
import Users.GeneralUser.UserGUI;

public class Lab extends Rooms{


	private final int minimumGroupSize=3;

	public Lab(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.soloBookable=false;
	}


	@Override
	public void book(Group group, Booking booking) {
		
		
		try {
			if(group.getStudentsNumber()<minimumGroupSize) {
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException e) {
			new ExceptionFrame("Required at least 3 Students to book this Room!");
			return;
		}		
		
		
		LabNotification notification=new LabNotification(group.getGroupID(), this.code, booking.getDate(), booking.getStartTime(), booking.getEndTime(), null, false);
		ReasonFrame reasonFrame=new ReasonFrame(notification);		
	}

}
