package Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

public class ProfessorNotification implements Notification{
	
	private String scheduleID;
	private String sender;
	private Date date;
	private Date newDate;
	private String newFrom;
	private String newTo;

	public ProfessorNotification(String scheduleID, Date date, String sender, Date newDate, String newFrom, String newTo) {
		super();
		this.scheduleID = scheduleID;
		this.sender = sender;
		this.date=date;
		this.newDate=newDate;
		this.newFrom=newFrom;
		this.newTo=newTo;
	}

	@Override
	public void accept() {
		// TODO Auto-generated method stub
		
	}

	public String getScheduleID() {
		return scheduleID;
	}

	public String getSender() {
		return sender;
	}

	public Date getDate() {
		return this.date;
	}	

	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public void setNewFrom(String newFrom) {
		this.newFrom = newFrom;
	}

	public void setNewTo(String newTo) {
		this.newTo = newTo;
	}

	public Date getNewDate() {
		return newDate;
	}

	public String getNewFrom() {
		return newFrom;
	}

	public String getNewTo() {
		return newTo;
	}

	@Override
	public String toString() {
		try {

			Connection conn=DBConnection.connect();
			String query="select Name, Last_Name from users where User_Code=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(this.sender));
			ResultSet result=preparedStmt.executeQuery();

			result.next();
			String name=result.getString(1);
			String lastName=result.getString(2);
			
			query="select * from schedule where Schedule_ID=?";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, this.scheduleID);
			result=preparedStmt.executeQuery();
			result.next();
			String startTime=result.getString(2);
			String endTime=result.getString(3);
			String room=result.getString(5);
			
			conn.close();
			
			if(this.newDate==null) {
			return name+" "+lastName+" requested a Schedule Swap the day: "+this.date+" from: "+startTime+" to: "+endTime+" in: "+room+" with no preference";
			}
			else {
				return name+" "+lastName+" requested a Schedule Swap the day: "+this.date+" from: "+this.newTo+" to: "+this.newFrom+" in: "+room+" with preference";	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	

		return null;
	}
}
