package Rooms;

import java.util.*;

import Exceptions.ExceptionFrame;

public abstract class Rooms implements Bookable, Comparable<Rooms>{

	protected int seats;
	protected String code;
	protected String type;
	protected boolean LIM;
	protected boolean outlets;
	protected boolean disabledAccess;
	protected boolean soloBookable;
	protected LinkedList<Booking> schedules;
	protected HashMap<String, Booking> availability;
	protected int maxOccupiedSeats;

	public Rooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		this.seats=Integer.parseInt(seats);
		this.code=code;
		this.type=type;
		this.LIM=Boolean.parseBoolean(LIM);
		this.outlets=Boolean.parseBoolean(outlets);
		this.disabledAccess=Boolean.parseBoolean(disabledAccess);
		this.schedules=new LinkedList<Booking>();
		this.availability=new HashMap<String, Booking>();
		this.maxOccupiedSeats=this.seats/2;
	}

	public int getSeats() {
		return this.seats;
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

	public boolean isLIM() {
		return LIM;
	}

	public boolean isOutlets() {
		return outlets;
	}

	public boolean isDisabledAccess() {
		return disabledAccess;
	}

	public boolean isSoloBookable() {
		return soloBookable;
	}
	
	public int getOccupiedSeats() {
		return this.maxOccupiedSeats;
	}

	public HashMap<String, Booking> getAvailability(){
		return this.availability;
	}

	public void setAvailability(String timeSpan, Booking availability) {
		this.availability.put(timeSpan, availability);
	}
	
	@Override
	public int compareTo(Rooms o) {
		return this.getCode().compareTo(o.getCode());
	}
	
	@Override
	public String toString() {
		return this.code;
	}
	
}
