package Rooms;

import Groups.Group;

public interface Bookable {

	public void book(Group group, Booking booking);
	
	default public void soloBook(String user, Booking booking) throws IllegalAccessError{
		throw new IllegalAccessError();
	}

}
