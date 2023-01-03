package Rooms;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JPanel;

import Exceptions.ExceptionFrame;
import Groups.Group;
import Notifications.LabNotification;
import Users.GeneralUser.UsersGUI;

public class Lab extends Rooms{



	public Lab(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.soloBookable=false;
	}


	@Override
	public void book(Group group, String date, String startTime, String endTime) {
		
		Date selectedDate=Date.valueOf(date);

		
		LabNotification notification=new LabNotification(group.getID(), this.code, selectedDate, startTime, endTime, null, false);
		ReasonFrame reasonFrame=new ReasonFrame(notification);
		
	}

}
