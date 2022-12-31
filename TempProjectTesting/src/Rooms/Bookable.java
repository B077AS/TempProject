package Rooms;

import Groups.Group;

public interface Bookable {
	
	public void book(Group group, String date, String startTime, String endTime);
	public void soloBook(String user, String date, String startTime, String endTime) throws Exception;

}
