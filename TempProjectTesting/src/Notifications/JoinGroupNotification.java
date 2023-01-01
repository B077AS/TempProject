package Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Groups.Group;

public class JoinGroupNotification implements Notification{

	private String sender;
	private String receiver;
	private String groupID;

	public JoinGroupNotification(String sender, String receiver, String groupID) {
		this.sender=sender;
		this.receiver=receiver;
		this.groupID=groupID;
	}


	public void accept() {
		try {
			Connection conn=DBConnection.connect();
			String query="insert into allgroups (Group_ID, Admin, Partecipant)"+"values (?, ? , ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, this.groupID);
			preparedStmt.setString(2, this.sender);
			preparedStmt.setInt(3, Integer.parseInt(this.receiver));
			preparedStmt.execute();
			
			conn.close();
			
			} catch (Exception ea) {
				ea.printStackTrace();
			}

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
			conn.close();
			return name+" "+lastName+" wants you to join the group: "+"'"+this.groupID+"'";

		} catch (Exception e) {
			e.printStackTrace();
		}	

		return null;
	}


	public String getSender() {
		return sender;
	}


	public String getReceiver() {
		return receiver;
	}


	public String getGroupID() {
		return groupID;
	}
	
	

}
