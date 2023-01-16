package Rooms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import MyTimer.Months;

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


/*	
	public void checkDate(String month, int day, int year) throws IllegalArgumentException{
		
		Date startDate;
		Date endDate;
		int monthNumber=Months.getMonths().get(month);
		
		Connection conn=DBConnection.connect();
		try {
			
			String query="select * from semester";
			Statement statement=conn.prepareStatement(query);
			ResultSet result=statement.executeQuery(query);
			result.next();
			startDate=result.getDate(1);
			endDate=result.getDate(2);

			conn.close();

		} catch (Exception e1) {
			e1.printStackTrace();;
			return;
		}
		
		int startDateString=Integer.parseInt(startDate.toString().split("-")[1]);
		int endDateString=Integer.parseInt(endDate.toString().split("-")[1]);
		
		LocalDateTime now=LocalDateTime.now();
		System.out.println(now.getYear());
		
		if(monthNumber>=startDateString && monthNumber<=12 && year==now.getYear()-1 || monthNumber>=1 && monthNumber<=endDateString && year==now.getYear()) {
			
		}else {
			throw new IllegalArgumentException();
		}
		
		
		
				
	}*/