package Rooms;

import java.sql.Date;

public class Booking implements Comparable<Booking>{

	private String startTime;
	private String endTime;
	private Date date;
	private Rooms room;
	private String bookingID;
	private String peopleID;
	private boolean locked; 


	public Booking(String startTime, String endTime, Date date,Rooms room, String bookingID, String peopleID, String locked) {
		this.startTime=startTime;
		this.endTime=endTime;
		this.date=date;
		this.room=room;
		this.bookingID=bookingID;
		this.peopleID=peopleID;
		this.locked=Boolean.parseBoolean(locked);
	}


	public String getStartTime() {
		return this.startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public Rooms getRoom() {
		return this.room;
	}


	public String getBookingID() {
		return bookingID;
	}


	public String getPeopleID() {
		return peopleID;
	}


	public boolean isLocked() {
		return locked;
	}

	public Date getDate() {
		return date;
	}


	@Override
	public int compareTo(Booking o) {
		return this.room.getCode().compareTo(o.getRoom().getCode());
	}

	@Override
	public String toString() {

		if(this.date==null) {
			return this.room.getCode()+" From: "+this.startTime+" To: "+this.endTime;
		}
		else {
			return this.date+" in Room: "+this.room.getCode()+" From: "+this.startTime+" To: "+this.endTime;
		}
	}

}
