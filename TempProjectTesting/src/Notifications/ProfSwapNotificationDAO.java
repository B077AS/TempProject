package Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DataBase.DBConnection;

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
	
	public ResultSet getNotifications(ProfessorSwapDraft notification) {
		
		try {

			Connection conn=DBConnection.connect();
			String query="select * from swap_notifications where Receiver=? or Sender=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getReceiver());
			statement.setString(2, notification.getSender());
			ResultSet result=statement.executeQuery();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
