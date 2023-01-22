package Notifications;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;

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

	public List<Notification> getNotifications(ProfessorNotification notification) {
		List<Notification> list=new ArrayList();
		try {
			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender!=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getSender());
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				list.add(new ProfessorNotification(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6)));
			}
			conn.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Date selectDate(ProfessorNotification notification) throws Exception {
			Connection conn=DBConnection.connect();
			
			String query="select New_Date from prof_notifications where Schedule_ID=? and Date=? and Sender=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, notification.getScheduleID());
			preparedStmt.setDate(2, Date.valueOf(notification.getDate()));
			preparedStmt.setString(3, notification.getSender());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			Date date=result.getDate(1);
			conn.close();
			return date;		
	}
	
	public void deleteNotificationNoNewDate(ProfessorNotification notification) throws Exception {
			Connection conn=DBConnection.connect();
			String query="delete from prof_notifications where Schedule_ID=? and Date=? and Sender=? and New_Date is NULL";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, notification.getScheduleID());
			preparedStmt.setDate(2, Date.valueOf(notification.getDate()));
			preparedStmt.setString(3, notification.getSender());
			System.out.println(preparedStmt);
			preparedStmt.executeUpdate();
			conn.close();
		
	}
	
	public void deleteWithNewDate(ProfessorNotification notification) throws Exception {
			Connection conn=DBConnection.connect();
			String query="delete from prof_notifications where Schedule_ID=? and Date=? and Sender=? and New_Date=? and New_From=? and New_To=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, notification.getScheduleID());
			preparedStmt.setDate(2, Date.valueOf(notification.getDate()));
			preparedStmt.setString(3, notification.getSender());
			preparedStmt.setDate(4, Date.valueOf(notification.getNewDate()));
			preparedStmt.setString(5, notification.getNewFrom());
			preparedStmt.setString(6, notification.getNewTo());
			preparedStmt.executeUpdate();
			conn.close();
	}

}
