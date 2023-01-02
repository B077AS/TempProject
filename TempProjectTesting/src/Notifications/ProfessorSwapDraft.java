package Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

public class ProfessorSwapDraft implements Notification{

	private String sender;
	private String receiver;
	private Date firstDate;
	private Date newDate;
	private String firstSchedule;
	private String newScehudle;
	private boolean accepted;

	public ProfessorSwapDraft(String sender, String receiver, Date firstDate, Date newDate, String firstSchedule,
			String newScehudle, String accepted) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.firstDate = firstDate;
		this.newDate = newDate;
		this.firstSchedule = firstSchedule;
		this.newScehudle = newScehudle;
		this.accepted=Boolean.parseBoolean(accepted);
	}

	@Override
	public void accept() {
		// TODO Auto-generated method stub

	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public String getFirstSchedule() {
		return firstSchedule;
	}

	public void setFirstSchedule(String firstSchedule) {
		this.firstSchedule = firstSchedule;
	}

	public String getNewScehudle() {
		return newScehudle;
	}

	public void setNewScehudle(String newScehudle) {
		this.newScehudle = newScehudle;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		try {
			Connection conn=DBConnection.connect();
			String query="select Start_Time, End_Time, Room from schedule where Schedule_ID=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, this.firstSchedule);
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			String firstFrom=result.getString(1);
			String firstTo=result.getString(2);
			String firstRoom=result.getString(3);

			query="select Start_Time, End_Time, Room from schedule where Schedule_ID=?";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, this.newScehudle);
			result=preparedStmt.executeQuery();
			result.next();
			String newFrom=result.getString(1);
			String newTo=result.getString(2);
			String newRoom=result.getString(3);

			conn.close();

			if(this.isAccepted()==false) {

				return "You have a PROPOSAL to swap from "+this.firstDate.toString()+" "+firstFrom+"-"+firstTo+" in "+firstRoom+" to "+this.newDate+" "+newFrom+"-"+newTo+" in "+newRoom;
			}
			else {
				return "You AGREED to swap from "+this.firstDate.toString()+" "+firstFrom+"-"+firstTo+" in "+firstRoom+" to "+this.newDate+" "+newFrom+"-"+newTo+" in "+newRoom;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	


		return null;
	}


}
