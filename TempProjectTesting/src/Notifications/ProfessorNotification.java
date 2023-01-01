package Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

public class ProfessorNotification implements Notification{
	
	private String scheduleID;
	private String sender;
	private String date;

	public ProfessorNotification(String scheduleID, String date, String sender) {
		super();
		this.scheduleID = scheduleID;
		this.sender = sender;
		this.date=date;
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

	public String getDate() {
		return this.date;
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
			return name+" "+lastName+" requested a Schedule Swap the day: "+this.date+" from: "+startTime+" to: "+endTime+" in: "+room;

		} catch (Exception e) {
			e.printStackTrace();
		}	

		return null;
	}
	
	

}
