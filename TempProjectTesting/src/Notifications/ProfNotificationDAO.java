package Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

public class ProfNotificationDAO {


	public boolean checkNotification(ProfessorNotification notification) {
		boolean resultFull=false;

		try {
			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender!=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getSender());
			ResultSet result=statement.executeQuery();

			resultFull=result.next();
			conn.close();
			return resultFull;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFull;

	}

	public ResultSet getNotifications(ProfessorNotification notification) {
		try {
			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender!=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getSender());
			ResultSet result=statement.executeQuery();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ProfessorNotification getNotificationNoSuggestion(ProfessorNotification notification) {
		try {
			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Schedule_ID=? and Date=? and Sender=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, notification.getScheduleID());
			preparedStmt.setDate(2, Date.valueOf(notification.getDate()));
			preparedStmt.setString(3, notification.getSender());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			ProfessorNotification date=new ProfessorNotification(null, result.getDate(2).toString(), null, null, null, null);
			conn.close();
			return date;
		} catch (Exception ex) {
		}
		return null;
		
	}
	
	public ProfessorNotification getNotificationSuggestion(ProfessorNotification notification) {
		try {
			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender=? and Date=? and New_Date=? and New_From=? and New_To=? and Schedule_ID=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, notification.getSender());
			preparedStmt.setDate(2, Date.valueOf(notification.getDate()));
			preparedStmt.setDate(3, Date.valueOf(notification.getNewDate()));
			preparedStmt.setString(4, notification.getNewFrom());
			preparedStmt.setString(5, notification.getNewTo());
			preparedStmt.setString(6, notification.getScheduleID());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			ProfessorNotification date=new ProfessorNotification(result.getString(1), result.getDate(2).toString(), result.getString(3), result.getDate(4).toString(), result.getString(5), result.getString(6));
			conn.close();
			return date;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
		
	}

}
