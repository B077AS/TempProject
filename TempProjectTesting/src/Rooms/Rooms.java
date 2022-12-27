package Rooms;

import java.util.*;

public abstract class Rooms implements Bookable{
	
	protected int seats;
	protected String code;
	protected String type;
	protected boolean LIM;
	protected boolean outlets;
	protected boolean disabledAccess;
	protected LinkedList<Booking> schedules;
	protected HashMap<String, Booking> availability;
	
	public Rooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		this.seats=Integer.parseInt(seats);
		this.code=code;
		this.type=type;
		this.LIM=Boolean.getBoolean(LIM);
		this.outlets=Boolean.getBoolean(outlets);
		this.disabledAccess=Boolean.getBoolean(disabledAccess);
		this.schedules=new LinkedList<Booking>();
		this.availability=new HashMap<String, Booking>();
	}
	
	public int getSeats() {
		return this.seats;
	}
	
	public void schedule(String startTime, String endTime, String subject, String day) {
		schedules.add(new Booking(startTime, endTime, subject, day));
	}
	
	public LinkedList<Booking> getSchedule(){
		return this.schedules;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getType() {
		return this.type;
	}
	
	public HashMap<String, Booking> getAvailability(){
		return this.availability;
	}
	
	public void setAvailability(String timeSpan, Booking availability) {
		this.availability.put(timeSpan, availability);
	}
}
