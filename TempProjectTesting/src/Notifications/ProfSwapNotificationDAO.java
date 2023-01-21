package Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

}
