package Rooms;

public class Booking implements Comparable<Booking>{
	
	private String startTime;
	private String endTime;
	private Rooms room;

	
	public Booking(String startTime, String endTime, Rooms room) {
		this.startTime=startTime;
		this.endTime=endTime;
		this.room=room;
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


	@Override
	public int compareTo(Booking o) {
		return this.room.getCode().compareTo(o.getRoom().getCode());
	}
	
	@Override
	public String toString() {
		return this.room.getCode()+" From: "+this.startTime+" To: "+this.endTime;
	}
	
}
