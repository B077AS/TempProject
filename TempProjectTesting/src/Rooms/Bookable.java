package Rooms;

import Groups.Group;

public interface Bookable {

	public void book(Group group, String date, String startTime, String endTime);
	
	default public void soloBook(String user, String date, String startTime, String endTime) throws IllegalAccessError{
		throw new IllegalAccessError();
	}

}
