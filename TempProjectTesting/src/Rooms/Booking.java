package Rooms;

import java.util.LinkedList;

public class Booking {
	
	private String startTime;
	private String endTime;
	private String subject;
	private String day;

	
	public Booking(String startTime, String endTime, String subject, String day) {
		this.startTime=startTime;
		this.endTime=endTime;
		this.subject=subject;
		this.day=day;
	}
	
	
	public String getStartTime() {
		return this.startTime;
	}
	
	public String getEndTime() {
		return this.endTime;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public String getDay() {
		return this.day;
	}
	
}
