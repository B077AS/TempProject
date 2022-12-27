package Rooms;

public interface Bookable {
	
	public void book();
	public void schedule(String startTime, String endTime, String subject,  String day);

}
