package Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataBase.DBConnection;
import Exceptions.ExceptionFrame;

public class ProfSwapNotificationDAO {

	public boolean checkNotification(ProfessorSwapDraft notification) {
		boolean swapRequests=false;
		try {

			Connection conn=DBConnection.connect();
			String query="select * from swap_notifications where Receiver=? and Accepted=? or Sender=? and Accepted=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement = conn.prepareStatement(query);
			statement.setString(1, notification.getReceiver());
			statement.setString(2, Boolean.toString(notification.isAccepted()));
			statement.setString(3, notification.getReceiver());
			statement.setString(4, Boolean.toString(notification.isAccepted()));
			ResultSet result=statement.executeQuery();

			swapRequests=result.next();
			conn.close();
			return swapRequests;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return swapRequests;
	}

	public List<Notification> getNotifications(ProfessorSwapDraft notification) {
		List<Notification> list=new ArrayList();
		try {

			Connection conn=DBConnection.connect();
			String query="select * from swap_notifications where Receiver=? or Sender=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getReceiver());
			statement.setString(2, notification.getSender());
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				list.add(new ProfessorSwapDraft(result.getString(1), result.getString(2), result.getDate(3), result.getDate(4), result.getString(5), result.getString(6), result.getString(7)));
			}
			conn.close();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void acceptSwap(ProfessorSwapDraft notification) throws Exception {
			Connection conn=DBConnection.connect();
			String query="UPDATE swap_notifications SET Accepted = 'true' WHERE Sender=? and Receiver=? and First_Date=? and New_Date=? and First_Schedule=? and New_Schedule=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, notification.getSender());
			preparedStmt.setString(2, notification.getReceiver());
			preparedStmt.setDate(3, notification.getFirstDate());
			preparedStmt.setDate(4, notification.getNewDate());
			preparedStmt.setString(5, notification.getFirstSchedule());
			preparedStmt.setString(6, notification.getNewScehudle());
			preparedStmt.executeUpdate();
			conn.close();
	}
	
	public void deleteSwap(ProfessorSwapDraft notification) throws Exception {
		Connection conn=DBConnection.connect();
		String query="delete from swap_notifications where Sender=? and Receiver=? and First_Date=? and New_Date=? and First_Schedule=? and New_Schedule=? and Accepted='false'";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, notification.getSender());
		preparedStmt.setString(2, notification.getReceiver());
		preparedStmt.setDate(3, notification.getFirstDate());
		preparedStmt.setDate(4, notification.getNewDate());
		preparedStmt.setString(5, notification.getFirstSchedule());
		preparedStmt.setString(6, notification.getNewScehudle());
		preparedStmt.executeUpdate();
	}

}
