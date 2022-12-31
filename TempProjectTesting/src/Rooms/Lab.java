package Rooms;

import javax.swing.JPanel;

import Exceptions.ExceptionFrame;
import Groups.Group;
import Users.GeneralUser.UsersGUI;

public class Lab extends Rooms{



	public Lab(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.soloBookable=false;
	}


	@Override
	public void book(Group group, String date, String startTime, String endTime) {
		// TODO Auto-generated method stub
		
	}

}
